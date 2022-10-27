package learn.wingspan.data;

import learn.wingspan.data.mappers.BadgeMapper;
import learn.wingspan.models.Badge;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BadgeJdbcTemplateRepository implements BadgeRepository {

    private final JdbcTemplate jdbcTemplate;

    public BadgeJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Badge> findAll() {
        final String sql = "select * from badge;";

        return jdbcTemplate.query(sql, new BadgeMapper());
    }


    @Override
    public Badge findById(int badgeId) {
        final String sql = "select badge_id, badge_name, badge_description, badge_img_url "
                + "from badge "
                + "where badge_id = ?;";
        return jdbcTemplate.query(sql, new BadgeMapper(), badgeId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Badge add(Badge badge) {
        final String sql = "insert into badge (badge_name, badge_description, badge_img_url)"
                + "values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, badge.getBadgeName());
            ps.setString(2, badge.getBadgeDescription());
            ps.setString(3, badge.getBadgeImgUrl());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        badge.setBadgeId(keyHolder.getKey().intValue());
        return badge;
    }

    @Override
    public boolean update(Badge badge) {
        final String sql = "update badge set "
                + "badge_name = ?, "
                + "badge_description = ?, "
                + "badge_img_url = ? "
                + "where badge_id = ?;";

        int rowsAffected = jdbcTemplate.update(sql,
                badge.getBadgeName(),
                badge.getBadgeDescription(),
                badge.getBadgeImgUrl(),
                badge.getBadgeId());

        return rowsAffected > 0;
    }

    @Transactional
    @Override
    public boolean deleteById(int badgeId) {
        jdbcTemplate.update("delete from user_badge where badge_id = ?;", badgeId);
        return jdbcTemplate.update("delete from badge where badge_id = ?;", badgeId) > 0;
    }
}
