package learn.wing_span.models;

import java.util.ArrayList;
import java.util.List;

public class Bird {
    private int birdId;
    private String commonName;
    private String scientificName;
    private String imageUrl;

    private List<Sighting> sightings = new ArrayList<>();

    public Bird(){

    }

    public Bird(int birdId, String commonName, String scientificName, String imageUrl) {
        this.birdId = birdId;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Sighting> getSightings(){
        return new ArrayList<>(sightings);
    }
}
