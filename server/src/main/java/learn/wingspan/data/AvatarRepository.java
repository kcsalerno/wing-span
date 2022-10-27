package learn.wingspan.data;

import learn.wingspan.models.Avatar;

import java.util.List;

public interface AvatarRepository {
    List<Avatar> findAll();

    Avatar findById(int avatarId);

    Avatar add(Avatar avatar);

    boolean update(Avatar avatar);

    boolean deleteById(int avatarId);
}
