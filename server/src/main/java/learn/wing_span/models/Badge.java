package learn.wing_span.models;

public class Badge {
    private int badgeId;
    private String badgeName;
    private String badgeDescription;
    private String badgeImgUrl;

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
}
