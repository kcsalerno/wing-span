package learn.wing_span.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Sighting {
    private int sightingId;
    private int sightingUserId;
    private int sightingBirdId;
    private LocalDate date;
    private String city;
    private String state;
    private boolean daytime;

    public  Sighting(){

    }
    public Sighting(int sightingId, LocalDate date, String city, String state, boolean daytime, int sightingUserId, int sightingBirdId) {
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

    public boolean getDaytime() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting that = (Sighting) o;
        return sightingId == that.sightingId &&
                Objects.equals(sightingUserId, that.sightingUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sightingId, sightingUserId);
    }
}
