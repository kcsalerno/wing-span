package learn.wing_span.data.mappers;

import learn.wing_span.models.Bird;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BirdMapper implements RowMapper<Bird> {
    @Override
    public Bird mapRow(ResultSet rs, int i) throws SQLException {
        Bird bird = new Bird();
        bird.setBirdId(rs.getInt("bird_id"));
        bird.setCommonName(rs.getString("common_name"));
        bird.setScientificName(rs.getString("scientific_name"));
        bird.setImageUrl(rs.getString("img_url"));
        return bird;
    }
}
