package learn.wingspan.domain;

import learn.wingspan.data.SightingRepository;
import learn.wingspan.data.SightingTraitRepository;
import learn.wingspan.models.Sighting;
import learn.wingspan.models.Trait;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SightingService {
    private final SightingRepository repository;
    private final SightingTraitRepository sightingTraitRepository;

    public SightingService(SightingRepository repository, SightingTraitRepository sightingTraitRepository) {
        this.repository = repository;
        this.sightingTraitRepository = sightingTraitRepository;
    }

    public List<Sighting> findAll() {
        return repository.findAll();
    }

    public Sighting findById(int sightingId) {
        return repository.findById(sightingId);
    }

    public Result<Sighting> create(Sighting sighting) {
        Result<Sighting> result = validate(sighting);

        List<Sighting> sightings = repository.findAll();
        for (Sighting s : sightings) {
            if (s.equals(sighting)) {
                result.addMessage(ResultType.DUPLICATE, "Sighting cannot be duplicated");
            }
        }

        if (!result.isSuccess()) {
            return result;
        }

        if (sighting.getSightingId() != 0) {
            result.addMessage(ResultType.INVALID, "Sighting ID cannot be set for `add` operation");
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
            if (repository.update(sighting)) {
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

        if (sighting.getSightingUserId() < 1) {
            result.addMessage(ResultType.INVALID, "User must valid");
            return result;
        }

        if (sighting.getSightingBirdId() < 1) {
            result.addMessage(ResultType.INVALID, "Bird must valid");
            return result;
        }

        if (sighting.getDate() == null) {
            result.addMessage(ResultType.INVALID, "Date cannot be null");
            return result;
        }

        if (!sighting.getDate().isBefore(LocalDate.now())) {
            result.addMessage(ResultType.INVALID, "Sighting entry must be before today");
            return result;
        }

        if (Validations.isNullOrBlank(sighting.getCity())) {
            result.addMessage(ResultType.INVALID, "City cannot be null");
            return result;
        }

        if (Validations.isNullOrBlank(sighting.getState())) {
            result.addMessage(ResultType.INVALID, "State cannot be null");
            return result;
        }

        if (sighting.getTraits().size() < 1) {
            result.addMessage(ResultType.INVALID, "At least one trait is required");
        }

        return result;
    }
}
