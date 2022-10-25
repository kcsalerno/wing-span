package learn.wing_span.data.mappers;

import learn.wing_span.models.Avatar;
import learn.wing_span.models.Badge;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BadgeMapper implements RowMapper<Badge> {
    @Override
    public Badge mapRow(ResultSet rs, int rowNum) throws SQLException {
        Badge badge = new Badge();
        badge.setBadgeId(rs.getInt("badge_id"));
        badge.setBadgeName(rs.getString("badge_name"));
        badge.setBadgeDescription(rs.getString("badge_description"));
        return badge;
    }
}
