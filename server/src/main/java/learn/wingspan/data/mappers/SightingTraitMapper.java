package learn.wingspan.data.mappers;

import learn.wingspan.models.SightingTrait;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SightingTraitMapper implements RowMapper<SightingTrait> {

    @Override
    public SightingTrait mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SightingTrait(
                rs.getInt("sighting_id"),
                rs.getInt("trait_id")
        );
    }
}
