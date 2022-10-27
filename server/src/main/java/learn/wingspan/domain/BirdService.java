package learn.wingspan.domain;

import learn.wingspan.data.BirdJdbcTemplateRepository;
import learn.wingspan.models.Bird;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BirdService {
    public final BirdJdbcTemplateRepository repository;

    public BirdService(BirdJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public List<Bird> findAll() {
        return repository.findAll();
    }

    public Bird findById(int birdId) {
        return repository.findById(birdId);
    }

    public Result<Bird> add(Bird bird) {
        Result<Bird> result = validate(bird);

        if (!result.isSuccess()) {
            return result;
        }
        if (bird.getBirdId() != 0) {
            result.addMessage(ResultType.INVALID, "Bird ID cannot be set for `add` operation.");
        }
        if (result.isSuccess()) {
            bird = repository.add(bird);
            result.setPayload(bird);
        }

        return result;
    }

    public Result<Bird> update(Bird bird) {
        Result<Bird> result = validate(bird);

        if (!result.isSuccess()) {
            return result;
        }
        if (bird.getBirdId() <= 0) {
            result.addMessage(ResultType.INVALID, "Bird ID must be set for `update` operation.");
        }
        if (result.isSuccess()) {
            if (repository.update(bird)) {
                result.setPayload(bird);
            } else {
                result.addMessage(ResultType.NOT_FOUND, "Bird ID not found.");
            }
        }

        return result;
    }

    public Result<Bird> deleteById(int birdId) {
        Result<Bird> result = new Result<>();

        if (!repository.deleteById(birdId)) {
            result.addMessage(ResultType.NOT_FOUND, "Bird ID not found.");
        }

        return result;
    }

    private Result<Bird> validate(Bird bird) {
        Result<Bird> result = new Result<>();

        if (bird == null) {
            result.addMessage(ResultType.INVALID, "Bird cannot be null.");
            return result;
        }
        if (Validations.isNullOrBlank(bird.getCommonName())) {
            result.addMessage(ResultType.INVALID, "A bird common name is required.");
        }
        if (Validations.isNullOrBlank(bird.getScientificName())) {
            result.addMessage(ResultType.INVALID, "A bird scientific name is required.");
        }

        List<Bird> birds = repository.findAll();
        for (Bird b : birds) {
            if (b.equals(bird)) {
                result.addMessage(ResultType.DUPLICATE, "A bird cannot be duplicated.");
                return result;
            }
        }

        return result;
    }
}
