package learn.wing_span.data;

import learn.wing_span.models.Avatar;

public interface AvatarRepository {
    Avatar findById(int avatarId);

    Avatar add(Avatar avatar);

    boolean update(Avatar avatar);

    boolean deleteById(int avatarId);
}
