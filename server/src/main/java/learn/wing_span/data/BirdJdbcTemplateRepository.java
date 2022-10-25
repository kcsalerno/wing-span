package learn.wing_span.data;

import learn.wing_span.models.Bird;
import learn.wing_span.models.Sighting;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BirdJdbcTemplateRepository implements BirdRepository{

    private final JdbcTemplate jdbcTemplate;

    public BirdJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Bird> findAll() {
        return null;
    }

    @Override
    public Bird findById(int BirdId) {
        return null;
    }

    @Override
    public Bird add(Bird bird) {
        return null;
    }

    @Override
    public boolean update(Bird bird) {
        return false;
    }

    @Override
    public boolean deleteById(int birdId) {
        return false;
    }

    private void addSightings(Sighting sighting) {
        String sql = "select ";
    }
}
