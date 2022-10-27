package learn.wingspan.data;

import learn.wingspan.data.mappers.SightingMapper;
import learn.wingspan.models.Sighting;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class SightingJdbcTemplateRepository implements SightingRepository {

    private final JdbcTemplate jdbcTemplate;

    public SightingJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Sighting> findAll() {

        final String sql = "select sighting_id, app_user_id, bird_id, sighting_date, city, state, daytime "
                + "from sighting;";
        return jdbcTemplate.query(sql, new SightingMapper());
    }

    @Override
    public Sighting findById(int sightingId) {
        final String sql = "select sighting_id, app_user_id, bird_id, sighting_date, city, state, daytime "
                + "from sighting "
                + "where sighting_id = ?;";

        return jdbcTemplate.query(sql, new SightingMapper(), sightingId).stream().findFirst().orElse(null);
    }

    @Override
    public Sighting create(Sighting sighting) {
        final String sql = "insert into sighting (app_user_id, bird_id, sighting_date, city, state, daytime) "
                + "values (?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sighting.getSightingUserId());
            statement.setInt(2, sighting.getSightingBirdId());
            statement.setDate(3, sighting.getDate() == null ? null : Date.valueOf(sighting.getDate()));
            statement.setString(4, sighting.getCity());
            statement.setString(5, sighting.getState());
            statement.setBoolean(6, sighting.isDaytime());
            return statement;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        sighting.setSightingId(keyHolder.getKey().intValue());
        return sighting;
    }

    @Override
    public boolean update(Sighting sighting) {
        final String sql = "update sighting set " +
                "app_user_id = ?, " +
                "bird_id = ?, " +
                "sighting_date = ?, " +
                "city = ?, " +
                "state = ?, " +
                "daytime = ? " +
                "where sighting_id = ?;";
        int rowsUpdated = jdbcTemplate.update(sql,
                sighting.getSightingUserId(),
                sighting.getSightingBirdId(),
                sighting.getDate(),
                sighting.getCity(),
                sighting.getState(),
                sighting.isDaytime(),
                sighting.getSightingId());

        return rowsUpdated > 0;
    }


    @Transactional
    @Override
    public boolean deleteById(int sightingId) {
        jdbcTemplate.update("delete from sighting_trait where sighting_id = ?;", sightingId);

        return jdbcTemplate.update("delete from sighting where sighting_id = ?;", sightingId) > 0;
    }

    // TODO add sightings
    // TODO add birds
}