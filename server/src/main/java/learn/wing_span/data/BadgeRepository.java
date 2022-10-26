package learn.wing_span.data;

import learn.wing_span.models.Badge;

import java.util.List;

public interface BadgeRepository {
    List<Badge> findAll();

    Badge findById(int badgeId);

    Badge add(Badge badge);

    boolean update(Badge badge);

    boolean deleteById(int badgeId);
}
