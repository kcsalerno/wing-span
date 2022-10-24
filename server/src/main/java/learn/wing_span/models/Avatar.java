package learn.wing_span.models;

public class Avatar {
    private int avatarId;
    private String imageUrl;
    private String avatarDescription;

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAvatarDescription() {
        return avatarDescription;
    }

    public void setAvatarDescription(String avatarDescription) {
        this.avatarDescription = avatarDescription;
    }
}
