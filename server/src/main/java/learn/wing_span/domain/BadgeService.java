package learn.wing_span.domain;

import learn.wing_span.data.BadgeJdbcTemplateRepository;
import learn.wing_span.models.Badge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    private final BadgeJdbcTemplateRepository repository;

    public BadgeService(BadgeJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public List<Badge> findAll() {
        return repository.findAll();
    }

    public Badge findById(int badgeId) {
        return repository.findById(badgeId);
    }

    public Result<Badge> add(Badge badge) {
        Result<Badge> result = validate(badge);

        if (!result.isSuccess()) {
            return result;
        }
        if (badge.getBadgeId() != 0) {
            result.addMessage(ResultType.INVALID, "Badge ID cannot be set for `add` operation.");
        }
        if (result.isSuccess()) {
            badge = repository.add(badge);
            result.setPayload(badge);
        }

        return result;
    }

    public Result<Badge> update(Badge badge) {
        Result<Badge> result = validate(badge);

        if (!result.isSuccess()) {
            return result;
        }
        if (badge.getBadgeId() <= 0) {
            result.addMessage(ResultType.INVALID, "Badge ID must be set for `update` operation.");
        }
        if ((result.isSuccess())) {
            if (repository.update(badge)) {
                result.setPayload(badge);
            } else {
                result.addMessage(ResultType.NOT_FOUND, "Badge ID not found.");
            }
        }

        return result;
    }

    public Result<Badge> deleteById(int badgeId) {
        Result<Badge> result = new Result<>();

        if (!repository.deleteById(badgeId)) {
            result.addMessage(ResultType.NOT_FOUND, "Badge ID not found.");
        }

        return result;
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
        if (repository.findAll().stream()
                .anyMatch(i -> i.getBadgeName().equalsIgnoreCase(badge.getBadgeName()) &&
                        i.getBadgeId() != badge.getBadgeId())) {
            result.addMessage(ResultType.DUPLICATE, "Badge name cannot be duplicated.");
        }
        if (repository.findAll().stream()
                .anyMatch(i -> i.getBadgeDescription().equalsIgnoreCase(badge.getBadgeDescription()) &&
                        i.getBadgeId() != badge.getBadgeId())) {
            result.addMessage(ResultType.DUPLICATE, "Badge description cannot be duplicated.");
        }
        if (repository.findAll().stream()
                .anyMatch(i -> i.getBadgeImgUrl().equalsIgnoreCase(badge.getBadgeImgUrl()) &&
                        i.getBadgeId() != badge.getBadgeId())) {
            result.addMessage(ResultType.DUPLICATE, "Badge image cannot be duplicated.");
        }

        return result;
    }
}
