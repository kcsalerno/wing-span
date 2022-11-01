package learn.wingspan.data;

public interface UserAvatarRepository {

    void add(int avatarId);
    
    void add(int appUserId, int avatarId);
}
