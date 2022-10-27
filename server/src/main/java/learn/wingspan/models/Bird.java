package learn.wingspan.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bird {
    private final List<Sighting> sightings = new ArrayList<>();
    private int birdId;
    private String commonName;
    private String scientificName;
    private String birdImageUrl;

    public Bird() {

    }

    public Bird(int birdId, String commonName, String scientificName, String imageUrl) {
        this.birdId = birdId;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.birdImageUrl = imageUrl;
    }

    public int getBirdId() {
        return birdId;
    }

    public void setBirdId(int birdId) {
        this.birdId = birdId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getBirdImageUrl() {
        return birdImageUrl;
    }

    public void setBirdImageUrl(String imageUrl) {
        this.birdImageUrl = imageUrl;
    }

    public List<Sighting> getSightings() {
        return new ArrayList<>(sightings);
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
        Bird bird = (Bird) obj;
        // Compare the fields between object and the forage.
        return (Objects.equals(commonName, bird.commonName)
                && Objects.equals(scientificName, bird.scientificName)
                && Objects.equals(birdImageUrl, bird.birdImageUrl));
    }

    @Override
    public int hashCode() {
        return Objects.hash(commonName, scientificName, birdImageUrl);
    }
}
