package learn.wingspan.data.mappers;

import learn.wingspan.models.Sighting;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SightingMapper implements RowMapper<Sighting> {
    @Override
    public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sighting sighting = new Sighting();
        sighting.setSightingId(rs.getInt("sighting_id"));
        sighting.setSightingUserId(rs.getInt("app_user_id"));
        sighting.setSightingBirdId(rs.getInt("bird_id"));
        if (rs.getDate("sighting_date") != null) {
            sighting.setDate(rs.getDate("sighting_date").toLocalDate());
        }
        sighting.setCity(rs.getString("city"));
        sighting.setState(rs.getString("state"));
        sighting.setDaytime(rs.getBoolean("daytime"));
        return sighting;
    }
}
