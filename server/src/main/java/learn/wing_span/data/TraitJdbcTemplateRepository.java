package learn.wing_span.data;

import learn.wing_span.data.mappers.TraitMapper;
import learn.wing_span.models.Trait;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class TraitJdbcTemplateRepository implements TraitRepository{

    private final JdbcTemplate jdbcTemplate;

    public TraitJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Trait> findAll() {
        final String sql = "select trait_id, name from trait;";
        return jdbcTemplate.query(sql, new TraitMapper());
    }

    @Override
    public Trait findById(int traitId) {
        final String sql = "select trait_id, name "
                + "from trait "
                + "where trait_id = ?";
        return jdbcTemplate.query(sql, new TraitMapper(), traitId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Trait add(Trait trait) {

        String sql = "insert into trait (name) values (?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update((connection) -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, trait.getName());
            return statement;
        }, keyHolder);

        if (rowsAffected == 0) {
            return null;
        }
        trait.setTraitId(keyHolder.getKey().intValue());
        return trait;
    }

    @Override
    public boolean update(Trait trait) {
        final String sql = "update trait set "
                + "name = ? "
                + "where trait_id = ?;";
        return jdbcTemplate.update(sql,
                trait.getName(),
                trait.getTraitId()) > 0;
    }

    @Override
    public boolean deleteById(int traitId) {
        final String sql = "delete from trait where trait_id = ?;";
        return jdbcTemplate.update(sql, traitId) > 0;
    }
}
