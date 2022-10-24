package learn.wing_span.data;

import learn.wing_span.data.mappers.SightingMapper;
import learn.wing_span.models.Sighting;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SightingJdbcTemplateRepository implements SightingRepository {

    private final JdbcTemplate jdbcTemplate;

    public SightingJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Sighting> findAll() throws DataAccessException {

        final String sql = "select sighting_id, app_user_id, bird_id, sighting_date, city, state, daytime from sighting;";
        return jdbcTemplate.query(sql, new SightingMapper());
    }

    @Override
    public Sighting findById(int sightingId) throws DataAccessException {
        return null;
    }

    @Override
    public Sighting create(Sighting sighting) throws DataAccessException {
        return null;
    }

    @Override
    public boolean update(Sighting sighting) throws DataAccessException {
        return false;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        return false;
    }
}
