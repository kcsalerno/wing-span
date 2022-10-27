package learn.wingspan.domain;

import learn.wingspan.data.TraitJdbcTemplateRepository;
import learn.wingspan.models.Trait;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraitService {

    private final TraitJdbcTemplateRepository repository;

    public TraitService(TraitJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public List<Trait> findAll() {
        return repository.findAll();
    }

    public Trait findById(int traitId) {
        return repository.findById(traitId);
    }

    public Result<Trait> add(Trait trait) {
        Result<Trait> result = validate(trait);

        if (!result.isSuccess()) {
            return result;
        }
        if (trait.getTraitId() != 0) {
            result.addMessage(ResultType.INVALID, "Trait ID cannot be set for `add` operation.");
        }
        if (result.isSuccess()) {
            trait = repository.add(trait);
            result.setPayload(trait);
        }

        return result;
    }

    public Result<Trait> update(Trait trait) {
        Result<Trait> result = validate(trait);

        if (!result.isSuccess()) {
            return result;
        }
        if (trait.getTraitId() <= 0) {
            result.addMessage(ResultType.INVALID, "Trait ID must be set for `update` operation.");
        }
        if (result.isSuccess()) {
            if (repository.update(trait)) {
                result.setPayload(trait);
            } else {
                result.addMessage(ResultType.NOT_FOUND, "Trait ID not found.");
            }
        }

        return result;
    }

    public Result<Trait> deleteById(int traitId) {
        Result<Trait> result = new Result<>();

        if (!repository.deleteById(traitId)) {
            result.addMessage(ResultType.NOT_FOUND, "Trait ID not found.");
        }

        return result;
    }

    private Result<Trait> validate(Trait trait) {
        Result<Trait> result = new Result<>();

        if (trait == null) {
            result.addMessage(ResultType.INVALID, "Trait cannot be null.");
        }
        if (Validations.isNullOrBlank(trait.getName())) {
            result.addMessage(ResultType.INVALID, "A trait name is required.");
        }
        if (repository.findAll().stream()
                .anyMatch(i -> i.getName().equalsIgnoreCase(trait.getName()) &&
                        i.getTraitId() != trait.getTraitId())) {
            result.addMessage(ResultType.DUPLICATE, "Trait name cannot be duplicated.");
        }

        return result;
    }

}
