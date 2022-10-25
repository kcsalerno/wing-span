package learn.wing_span.data;

import learn.wing_span.models.AppUser;
import org.springframework.transaction.annotation.Transactional;

public interface AppUserRepository {
    @Transactional
    AppUser findByUsername(String username);

    @Transactional
    AppUser findByEmail(String email);

    @Transactional
    AppUser create(AppUser user);

//    @Transactional
//    boolean update(AppUser user);

    @Transactional
    void update(AppUser user);
}
