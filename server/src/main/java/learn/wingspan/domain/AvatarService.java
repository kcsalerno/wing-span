package learn.wingspan.domain;

import learn.wingspan.data.AvatarJdbcTemplateRepository;
import learn.wingspan.models.Avatar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvatarService {
    public final AvatarJdbcTemplateRepository repository;

    public AvatarService(AvatarJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public List<Avatar> findAll() {
        return repository.findAll();
    }

    public Avatar findById(int avatarId) {
        return repository.findById(avatarId);
    }

    public Result<Avatar> add(Avatar avatar) {
        Result<Avatar> result = validate(avatar);

        if (!result.isSuccess()) {
            return result;
        }
        if (avatar.getAvatarId() != 0) {
            result.addMessage(ResultType.INVALID, "Avatar ID cannot be set for `add` operation.");
        }
        if (result.isSuccess()) {
            avatar = repository.add(avatar);
            result.setPayload(avatar);
        }

        return result;
    }

    public Result<Avatar> update(Avatar avatar) {
        Result<Avatar> result = validate(avatar);

        if (!result.isSuccess()) {
            return result;
        }
        if (avatar.getAvatarId() <= 0) {
            result.addMessage(ResultType.INVALID, "Avatar ID must be set for `update` operation.");
        }
        if (result.isSuccess()) {
            if (repository.update(avatar)) {
                result.setPayload(avatar);
            } else {
                result.addMessage(ResultType.NOT_FOUND, "Avatar ID not found.");
            }
        }

        return result;
    }

    public Result<Avatar> deleteById(int avatarId) {
        Result<Avatar> result = new Result<>();

        if (!repository.deleteById(avatarId)) {
            result.addMessage(ResultType.NOT_FOUND, "Avatar ID not found.");
        }

        return result;
    }

    private Result<Avatar> validate(Avatar avatar) {
        Result<Avatar> result = new Result<>();

        if (avatar == null) {
            result.addMessage(ResultType.INVALID, "Avatar cannot be null.");
            return result;
        }
        if (Validations.isNullOrBlank(avatar.getAvatarDescription())) {
            result.addMessage(ResultType.INVALID, "A description is required.");
        }
        if (Validations.isNullOrBlank(avatar.getAvatarImageUrl())) {
            result.addMessage(ResultType.INVALID, "An image url is required.");
        }

        List<Avatar> avatars = repository.findAll();
        for (Avatar a : avatars) {
            if (a.equals(avatar)) {
                result.addMessage(ResultType.DUPLICATE, "An avatar cannot be duplicated.");
                return result;
            }
        }

        return result;
    }
}
