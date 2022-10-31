package learn.wingspan.models;

public class UserAvatar {
    private int userId;
    private int avatarId;

    public UserAvatar(int userId, int avatarId) {
        this.userId = userId;
        this.avatarId = avatarId;
    }

    public UserAvatar() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }
}
