package learn.wing_span.data;

import learn.wing_span.data.mappers.AvatarMapper;
import learn.wing_span.models.Avatar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AvatarJdbcTemplateRepository implements AvatarRepository {
    private final JdbcTemplate jdbcTemplate;

    public AvatarJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Avatar> findAll() {
        final String sql = "select avatar_id, avatar_img_url, avatar_description "
                + "from avatar;";

        return jdbcTemplate.query(sql, new AvatarMapper());
    }

    @Override
    public Avatar findById(int avatarId) {
        final String sql = "select avatar_id, avatar_img_url, avatar_description "
                + "from avatar "
                + "where avatar_id = ?;";
        return jdbcTemplate.query(sql, new AvatarMapper(), avatarId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Avatar add(Avatar avatar) {
        final String sql = "insert into avatar (avatar_img_url, avatar_description)"
                + "values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, avatar.getAvatarImageUrl());
            ps.setString(2, avatar.getAvatarDescription());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        avatar.setAvatarId(keyHolder.getKey().intValue());
        return avatar;
    }

    @Override
    public boolean update(Avatar avatar) {
        final String sql = "update avatar set "
                + "avatar_img_url = ?, "
                + "avatar_description = ? "
                + "where avatar_id = ?;";

        int rowsAffected = jdbcTemplate.update(sql,
                avatar.getAvatarImageUrl(),
                avatar.getAvatarDescription(),
                avatar.getAvatarId());

        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int avatarId) {
        jdbcTemplate.update("delete from user_avatar where avatar_id = ?;", avatarId);
        return jdbcTemplate.update("delete from avatar where avatar_id = ?", avatarId) > 0;
    }
}
