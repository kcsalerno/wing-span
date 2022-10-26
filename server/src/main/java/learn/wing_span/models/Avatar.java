package learn.wing_span.models;

import java.util.Objects;

public class Avatar {
    private int avatarId;
    private String avatarImageUrl;
    private String avatarDescription;

    public Avatar(int avatarId, String imageUrl, String avatarDescription) {
        this.avatarId = avatarId;
        this.avatarImageUrl = imageUrl;
        this.avatarDescription = avatarDescription;
    }

    public Avatar() {
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public String getAvatarImageUrl() {
        return avatarImageUrl;
    }

    public void setAvatarImageUrl(String imageUrl) {
        this.avatarImageUrl = imageUrl;
    }

    public String getAvatarDescription() {
        return avatarDescription;
    }

    public void setAvatarDescription(String avatarDescription) {
        this.avatarDescription = avatarDescription;
    }

    @Override
    public boolean equals(Object obj) {
        // If this is the object, return true.
        if (this == obj)
            return true;
        // Check that object is not null, nor is the object's class different from the current runtime class,
        // if either is true, return false.
        if (obj == null || getClass() != obj.getClass())
            return false;
        // Cast the object's type.
        Avatar avatar = (Avatar) obj;
        // Compare the fields between object and the forage.
        return (Objects.equals(avatarImageUrl, avatar.avatarImageUrl)
                && Objects.equals(avatarDescription, avatar.avatarDescription));
    }

    @Override
    public int hashCode() {
        return Objects.hash(avatarImageUrl, avatarDescription);
    }
}
