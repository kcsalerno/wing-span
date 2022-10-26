package learn.wing_span.data.mappers;

import learn.wing_span.models.Avatar;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AvatarMapper implements RowMapper<Avatar> {
    @Override
    public Avatar mapRow(ResultSet rs, int rowNum) throws SQLException {
        Avatar avatar = new Avatar();
        avatar.setAvatarId(rs.getInt("avatar_id"));
        avatar.setAvatarImageUrl(rs.getString("avatar_img_url"));
        avatar.setAvatarDescription(rs.getString("avatar_description"));
        return avatar;
    }
}
