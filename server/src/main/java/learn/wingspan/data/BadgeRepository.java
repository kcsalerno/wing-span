package learn.wingspan.data;

import learn.wingspan.models.Badge;

import java.util.List;

public interface BadgeRepository {
    List<Badge> findAll();

    Badge findById(int badgeId);

    Badge add(Badge badge);

    boolean update(Badge badge);

    boolean deleteById(int badgeId);
}
