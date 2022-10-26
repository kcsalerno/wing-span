package learn.wing_span.data.mappers;

import learn.wing_span.models.Trait;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TraitMapper implements RowMapper<Trait> {
    @Override
    public Trait mapRow(ResultSet rs, int rowNum) throws SQLException {
        Trait trait = new Trait();
        trait.setTraitId(rs.getInt("trait_id"));
        trait.setName(rs.getString("name"));
        return trait;
    }
}
