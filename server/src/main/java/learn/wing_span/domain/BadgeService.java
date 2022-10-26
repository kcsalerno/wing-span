package learn.wing_span.domain;

import learn.wing_span.data.BadgeJdbcTemplateRepository;
import learn.wing_span.models.Badge;
import org.springframework.stereotype.Service;

@Service
public class BadgeService {

    private final BadgeJdbcTemplateRepository repository;

    public BadgeService(BadgeJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public Badge findById(int badgeId) {
        return repository.findById(badgeId);
    }

    private Result<Badge> validate(Badge badge) {
        Result<Badge> result = new Result<>();

        if (badge == null) {
            result.addMessage(ResultType.INVALID, "Badge cannot be null.");
        }
        if (Validations.isNullOrBlank(badge.getBadgeName())) {
            result.addMessage(ResultType.INVALID, "A badge name is required.");
        }
        if (Validations.isNullOrBlank(badge.getBadgeDescription())) {
            result.addMessage(ResultType.INVALID, "A badge description is required.");
        }
        if (Validations.isNullOrBlank(badge.getBadgeImgUrl())) {
            result.addMessage(ResultType.INVALID, "A badge image is required.");
        }

        return result;
    }
}
