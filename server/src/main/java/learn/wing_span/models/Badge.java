package learn.wing_span.models;

import java.util.Objects;

public class Badge {
    private int badgeId;
    private String badgeName;
    private String badgeDescription;
    private String badgeImgUrl;

    public Badge(int badgeId, String badgeName, String badgeDescription, String badgeImgUrl) {
        this.badgeId = badgeId;
        this.badgeName = badgeName;
        this.badgeDescription = badgeDescription;
        this.badgeImgUrl = badgeImgUrl;
    }

    public Badge() {
    }

    public String getBadgeImgUrl() {
        return badgeImgUrl;
    }

    public void setBadgeImgUrl(String badgeImgUrl) {
        this.badgeImgUrl = badgeImgUrl;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getBadgeDescription() {
        return badgeDescription;
    }

    public void setBadgeDescription(String badgeDescription) {
        this.badgeDescription = badgeDescription;
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
        Badge badge = (Badge) obj;
        // Compare the fields between object and the forage.
        return (Objects.equals(badgeName, badge.badgeName)
                && Objects.equals(badgeDescription, badge.badgeDescription)
                && Objects.equals(badgeImgUrl, badge.badgeImgUrl));
    }

    @Override
    public int hashCode() {
        return Objects.hash(badgeName, badgeDescription, badgeImgUrl);
    }
}
