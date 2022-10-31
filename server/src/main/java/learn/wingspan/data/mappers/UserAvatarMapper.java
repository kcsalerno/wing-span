package learn.wingspan.data.mappers;

import learn.wingspan.models.UserAvatar;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAvatarMapper implements RowMapper<UserAvatar> {
    @Override
    public UserAvatar mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserAvatar(
                rs.getInt("app_user_id"),
                rs.getInt("avatar_id")
        );
    }
}
