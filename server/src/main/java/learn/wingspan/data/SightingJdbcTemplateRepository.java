package learn.wingspan.data;

import learn.wingspan.data.mappers.SightingMapper;
import learn.wingspan.data.mappers.TraitMapper;
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

        final String sql = "select s.sighting_id, s.app_user_id, s.bird_id, s.sighting_date, s.city, s.state, s.daytime, au.username "
                + "from sighting s "
                + "inner join app_user au on s.app_user_id = au.app_user_id;";
//        return jdbcTemplate.query(sql, new SightingMapper());

        List<Sighting> sightings = jdbcTemplate.query(sql, new SightingMapper());

        if (sightings.size() > 0) {
            for (Sighting sighting : sightings) {
                addTraits(sighting);
            }
        }

        return sightings;
    }

    @Override
    public Sighting findById(int sightingId) {
        final String sql = "select s.sighting_id, s.app_user_id, s.bird_id, s.sighting_date, s.city, s.state, s.daytime, au.username "
                + "from sighting s "
                + "inner join app_user au on s.app_user_id = au.app_user_id "
                + "where sighting_id = ?;";

//        return jdbcTemplate.query(sql, new SightingMapper(), sightingId).stream().findFirst().orElse(null);
        Sighting sighting = jdbcTemplate.query(sql, new SightingMapper(), sightingId).stream()
                .findFirst().orElse(null);

        if (sighting != null) {
            addTraits(sighting);
        }

        return sighting;
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

    private void addTraits(Sighting sighting) {
        final String sql = "select * from trait t "
                + "inner join sighting_trait st on t.trait_id = st.trait_id "
                + "inner join sighting s on st.sighting_id = s.sighting_id "
                + "where s.sighting_id = ?;";

        var traits = jdbcTemplate.query(sql, new TraitMapper(), sighting.getSightingId());
        sighting.setTraits(traits);
    }
    // TODO add birds
}
