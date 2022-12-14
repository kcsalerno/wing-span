package learn.wingspan.security;

import learn.wingspan.data.AppUserRepository;
import learn.wingspan.data.UserAvatarRepository;
import learn.wingspan.domain.Result;
import learn.wingspan.domain.ResultType;
import learn.wingspan.domain.Validations;
import learn.wingspan.models.AppUser;
import learn.wingspan.models.Avatar;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final UserAvatarRepository userAvatarRepository;
    private final PasswordEncoder passwordEncoder;
    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    Pattern pattern = Pattern.compile(regex);
    private List<UserDetails> users;

    public AppUserService(AppUserRepository appUserRepository, UserAvatarRepository userAvatarRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.userAvatarRepository = userAvatarRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);

        if (appUser == null || !appUser.isEnabled()) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return appUser;
    }

    public Result<AppUser> create(String username, String password, String email, int avatarId, String avatarDescription, String avatarImageUrl) {
        Result<AppUser> result = validate(username, password, email);
        if (!result.isSuccess()) {
            return result;
        }

        password = passwordEncoder.encode(password);

        AppUser appUser = new AppUser(0, username, password, true, email, List.of("USER"));

        Avatar avatar = new Avatar(avatarId, avatarDescription, avatarImageUrl);

        appUser.setAvatar(avatar);

        try {
            appUser = appUserRepository.create(appUser);
            userAvatarRepository.add(appUser.getAppUserId(), avatarId);
            result.setPayload(appUser);
        } catch (DuplicateKeyException ex) {
            result.addMessage(ResultType.INVALID, "The provided username already exists");
        }

        return result;
    }

    private Result<AppUser> validate(String username, String password, String email) {
        Result<AppUser> result = new Result<>();
        if (Validations.isNullOrBlank(username)) {
            result.addMessage(ResultType.INVALID, "username is required");
            return result;
        }

        if (Validations.isNullOrBlank(password)) {
            result.addMessage(ResultType.INVALID, "password is required");
            return result;
        }

        if (Validations.isNullOrBlank(email)) {
            result.addMessage(ResultType.INVALID, "email is required");
            return result;
        }

        if (username.length() > 50) {
            result.addMessage(ResultType.INVALID, "username must be less than 50 characters");
        }

        if (!isValidPassword(password)) {
            result.addMessage(ResultType.INVALID,
                    "password must be at least 8 characters and contain a digit," +
                            " a letter, and a non-digit/non-letter");
        }

        if (!isValidEmail(email)) {
            result.addMessage(ResultType.INVALID, "email must be valid format");
        }

        return result;
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        int digits = 0;
        int letters = 0;
        int others = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digits++;
            } else if (Character.isLetter(c)) {
                letters++;
            } else {
                others++;
            }
        }

        return digits > 0 && letters > 0 && others > 0;
    }

    private boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}