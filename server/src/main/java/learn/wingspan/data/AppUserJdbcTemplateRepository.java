package learn.wingspan.data;

import learn.wingspan.data.mappers.AppUserMapper;
import learn.wingspan.data.mappers.AvatarMapper;
import learn.wingspan.models.AppUser;
import learn.wingspan.models.Avatar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

@Repository
public class AppUserJdbcTemplateRepository implements AppUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public AppUser findByUsername(String username) {
        List<String> roles = getRolesByUsername(username);

        final String sql = "select app_user_id, username, password_hash, enabled, email "
                + "from app_user "
                + "where username = ?;";

        AppUser appUser = jdbcTemplate.query(sql, new AppUserMapper(roles), username)
                .stream()
                .findFirst().orElse(null);

        if (appUser != null) {
            addAvatar(appUser);
        }

        return appUser;
    }

    @Override
    @Transactional
    public AppUser create(AppUser user) {

        final String sql = "insert into app_user (username, password_hash, email) values (?, ?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setAppUserId(keyHolder.getKey().intValue());

        updateRoles(user);

        return user;
    }

    @Override
    @Transactional
    public boolean update(AppUser user) {

        final String sql = "update app_user set "
                + "username = ?, "
                + "enabled = ?, "
                + "email = ? "
                + "where app_user_id = ?";

//        jdbcTemplate.update(sql,
//                user.getUsername(), user.isEnabled(), user.getEmail(), user.getAppUserId());
//
//        updateRoles(user);
        boolean updated = jdbcTemplate.update(sql,
                user.getUsername(), user.isEnabled(), user.getEmail(), user.getAppUserId()) > 0;

        if (updated) {
            updateRoles(user);
        }

        return updated;
    }

    private void updateRoles(AppUser user) {
        // delete all roles, then re-add
        jdbcTemplate.update("delete from app_user_role where app_user_id = ?;", user.getAppUserId());

        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if (authorities == null) {
            return;
        }

        for (GrantedAuthority role : authorities) {
            String sql = "insert into app_user_role (app_user_id, app_role_id) "
                    + "select ?, app_role_id from app_role where `name` = ?;";
            jdbcTemplate.update(sql, user.getAppUserId(), role.getAuthority());
        }
    }

    private List<String> getRolesByUsername(String username) {
        final String sql = "select r.name "
                + "from app_user_role ur "
                + "inner join app_role r on ur.app_role_id = r.app_role_id "
                + "inner join app_user au on ur.app_user_id = au.app_user_id "
                + "where au.username = ?";
        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), username);
    }

    private void addAvatar(AppUser appUser) {
        final String sql = "select a.avatar_id, a.avatar_img_url, a.avatar_description from app_user au "
                + "inner join user_avatar ua on au.app_user_id = ua.app_user_id "
                + "inner join avatar a on ua.avatar_id = a.avatar_id "
                + "where au.app_user_id = ?";

        var avatar = jdbcTemplate.queryForObject(sql, new AvatarMapper(), appUser.getAppUserId());

        appUser.setAvatar(avatar);
    }
}

//    @Override
//    @Transactional
//    public AppUser findByEmail(String email) {
//        List<String> roles = getRolesByEmail(email);
//
//        final String sql = "select app_user_id, username, password_hash, enabled, email, user_first_name, user_last_name "
//                + "from app_user "
//                + "where email = ?;";
//
//        return jdbcTemplate.query(sql, new AppUserMapper(roles), email)
//                .stream()
//                .findFirst().orElse(null);
//    }

//    private List<String> getRolesByEmail(String email) {
//        final String sql = "select r.name "
//                + "from app_user_role ur "
//                + "inner join app_role r on ur.app_role_id = r.app_role_id "
//                + "inner join app_user au on ur.app_user_id = au.app_user_id "
//                + "where au.email = ?;";
//
//        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), email);
//    }