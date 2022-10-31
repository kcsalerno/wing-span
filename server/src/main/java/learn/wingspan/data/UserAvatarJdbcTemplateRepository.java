package learn.wingspan.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class UserAvatarJdbcTemplateRepository implements UserAvatarRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserAvatarJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(int appUserId, int avatarId) {
        final String sql = "insert into user_avatar (app_user_id, avatar_id) "
                + "values (?, ?);";

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appUserId);
            ps.setInt(2, avatarId);

            return ps;
        });

        if (rowsAffected <= 0) {
            return;
        }
    }
}
