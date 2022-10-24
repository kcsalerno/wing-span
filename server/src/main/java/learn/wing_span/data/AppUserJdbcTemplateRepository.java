package learn.wing_span.data;

import learn.wing_span.data.mappers.AppUserMapper;
import learn.wing_span.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class AppUserJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<GrantedAuthority> authorityMapper = (ResultSet rs, int index) -> {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(rs.getString("name"));
        return authority;
    };
    public AppUser findByEmail(String email) {
        String sql = "select app_user_id, username, password_hash, enabled, email, user_first_name, user_last_name "
                + "from app_user "
                + "where email = ?;";

        AppUser user = jdbcTemplate.query(sql, new AppUserMapper(), email).stream()
                .findFirst().orElse(null);

        if (user != null) {
            attachAuthorities(user);
        }
        return user;
    }

    private void attachAuthorities(AppUser user) {

        String sql = "select ar.`name` "
                + "from app_user_role aur "
                + "inner join app_role ar on aur.app_role_id = ar.app_role_id "
                + "where aur.app_user_id = ?;";

        List<GrantedAuthority> authorities = jdbcTemplate.query(sql, authorityMapper, user.getAppUserId());

        user.setAuthorities(authorities);
    }
}
