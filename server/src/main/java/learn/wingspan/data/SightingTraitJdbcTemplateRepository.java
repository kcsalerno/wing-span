package learn.wingspan.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class SightingTraitJdbcTemplateRepository implements SightingTraitRepository {
    private final JdbcTemplate jdbcTemplate;

    public SightingTraitJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void add(int sightingId, int traitId) {
        final String sql = "insert into sighting_trait (sighting_id, trait_id) "
                + "values (?, ?);";

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sightingId);
            ps.setInt(2, traitId);

            return ps;
        });

        if (rowsAffected <= 0) {
            return;
        }
    }


    //update
}
