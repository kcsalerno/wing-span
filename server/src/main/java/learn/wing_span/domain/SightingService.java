package learn.wing_span.domain;

import learn.wing_span.data.SightingRepository;
import learn.wing_span.models.Sighting;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class SightingService {
    private final SightingRepository repository;
    private final Validator validator;

    public SightingService(SightingRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<Sighting> findAll() {
        return repository.findAll();
    }

    public Sighting findById(int sightingId) {
        return repository.findById(sightingId);
    }

    public Result<Sighting> create(Sighting sighting) {
        Result<Sighting> result = validate(sighting);

        if (!result.isSuccess()) {
            return result;
        }

        if (sighting.getSightingId() != 0) {
            result.addMessage(ResultType.INVALID, "Sighting ID cannot be set for `add` operation");
            return result;
        }

        if (result.isSuccess()) {
            sighting = repository.create(sighting);
            result.setPayload(sighting);
        }

        return result;
    }

    public Result<Sighting> update(Sighting sighting) {
        Result<Sighting> result = validate(sighting);

        if (!result.isSuccess()) {
            return result;
        }
        if (sighting.getSightingId() <= 0) {
            result.addMessage(ResultType.INVALID, "Sighting ID must be set for `update` operation");
            return result;
        }

        if (result.isSuccess()) {
            if (!repository.update(sighting)) {
                result.setPayload(sighting);
            } else {
                String msg = String.format("Sighting ID: %s, not found", sighting.getSightingId());
                result.addMessage(ResultType.NOT_FOUND, msg);
            }
        }
        return result;
    }

    public Result<Sighting> deleteById(int sightingId) {
        Result<Sighting> result = new Result<>();

        if (!repository.deleteById(sightingId)) {
            result.addMessage(ResultType.NOT_FOUND, "Sighting ID not found");
        }

        return result;
    }

    private Result<Sighting> validate(Sighting sighting) {
        Result<Sighting> result = new Result<>();

        if (sighting == null) {
            result.addMessage(ResultType.INVALID, "Sighting cannot be null");
            return result;
        }

        if (sighting.getSightingUserId() < 0) {
            result.addMessage(ResultType.INVALID, "User must exist");
            return result;
        }

        if (sighting.getSightingBirdId() < 0) {
            result.addMessage(ResultType.INVALID, "Bird must exist");
            return result;
        }

        if (sighting.getDate() == null) {
            result.addMessage(ResultType.INVALID, "Date cannot be null");
            return result;
        }

        if (sighting.getDate() != null && sighting.getDate().isBefore(LocalDate.now())) {
            result.addMessage(ResultType.INVALID, "Sighting entry must be before today");
            return result;
        }

        if (Validations.isNullOrBlank(sighting.getCity())) {
            result.addMessage(ResultType.INVALID, "City cannot be null");
            return result;
        }

        Set<ConstraintViolation<Sighting>> violations = validator.validate(sighting);
        for (var violation : violations) {
            result.addMessage(ResultType.INVALID, violation.getMessage());
            return result;
        }

        return result;
    }
}
