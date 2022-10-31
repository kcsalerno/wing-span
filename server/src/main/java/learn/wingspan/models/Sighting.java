package learn.wingspan.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sighting {
    private int sightingId;
    private int sightingUserId;
    private int sightingBirdId;
    private LocalDate date;
    private String city;
    private String state;
    private boolean daytime;
    private String username;
    private Bird bird;
    private List<Trait> traits = new ArrayList<>();

    public Sighting() {

    }

    public Sighting(int sightingId, int sightingUserId, int sightingBirdId, LocalDate date, String city, String state, boolean daytime) {
        this.sightingId = sightingId;
        this.date = date;
        this.city = city;
        this.state = state;
        this.daytime = daytime;
        this.sightingUserId = sightingUserId;
        this.sightingBirdId = sightingBirdId;
    }

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isDaytime() {
        return daytime;
    }

    public void setDaytime(boolean daytime) {
        this.daytime = daytime;
    }

    public int getSightingUserId() {
        return sightingUserId;
    }

    public void setSightingUserId(int sightingUserId) {
        this.sightingUserId = sightingUserId;
    }

    public int getSightingBirdId() {
        return sightingBirdId;
    }

    public void setSightingBirdId(int sightingBirdId) {
        this.sightingBirdId = sightingBirdId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
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
        Sighting sighting = (Sighting) obj;
        // Compare the fields between object and the forage.
        return (sightingUserId == sighting.sightingUserId
                && sightingBirdId == sighting.sightingBirdId
                && Objects.equals(date, sighting.date)
                && Objects.equals(city, sighting.city)
                && Objects.equals(state, sighting.state)
                && Objects.equals(daytime, sighting.daytime));
    }

    @Override
    public int hashCode() {
        return Objects.hash(sightingUserId, sightingBirdId, date, city, state, daytime);
    }
}
