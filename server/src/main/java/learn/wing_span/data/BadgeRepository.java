package learn.wing_span.data;

import learn.wing_span.models.Avatar;
import learn.wing_span.models.Badge;

public interface BadgeRepository {
    Badge findById(int badgeId);
    Badge add(Badge badge);
    boolean update(Badge badge);
    boolean deleteById(int badgeId);
}
