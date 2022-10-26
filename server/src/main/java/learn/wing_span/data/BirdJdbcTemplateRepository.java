package learn.wing_span.data;

import learn.wing_span.data.mappers.BirdMapper;
import learn.wing_span.data.mappers.SightingMapper;
import learn.wing_span.models.Bird;
import learn.wing_span.models.Sighting;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BirdJdbcTemplateRepository implements BirdRepository{

    private final JdbcTemplate jdbcTemplate;

    public BirdJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Bird> findAll() {
        final String sql = "select bird_id, common_name, scientific_name, img_url "
                + "from bird;";
        return jdbcTemplate.query(sql, new BirdMapper());
    }

    @Override
    public Bird findById(int birdId) {
        final String sql = "select bird_id, common_name, scientific_name, img_url "
                + "from bird "
                + "where bird_id = ?;";
        return jdbcTemplate.query(sql, new BirdMapper(), birdId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Bird add(Bird bird) {
        final String sql = "insert into bird (common_name, scientific_name, img_url) "
                + "values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, bird.getCommonName());
            statement.setString(2, bird.getScientificName());
            statement.setString(3, bird.getBirdImageUrl());
            return statement;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        bird.setBirdId(keyHolder.getKey().intValue());
        return bird;
    }

    @Override
    public boolean update(Bird bird) {
        final String sql = "update bird set "
                + "common_name = ?, "
                + "scientific_name = ?, "
                + "img_url = ? "
                + "where bird_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                bird.getCommonName(),
                bird.getScientificName(),
                bird.getBirdImageUrl(),
                bird.getBirdId());

        return rowsUpdated > 0;
    }

    @Transactional
    @Override
    public boolean deleteById(int birdId) {
        List<Integer> sightingIds = getSightingIds(birdId);

        for (Integer id : sightingIds) {
            jdbcTemplate.update("delete from sighting_trait where sighting_id = ?;", id);
            jdbcTemplate.update("delete from sighting where sighting_id = ?;", id);
        }

        return jdbcTemplate.update("delete from bird where bird_id = ?;", birdId) > 0;
    }

    private List<Integer> getSightingIds(int birdId) {
        String sql = "select * from sighting where bird_id = ?;";

        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getInt("sighting_id"), birdId);
    }
}
