package learn.wingspan.models;

public class SightingTrait {
    private int sighting_id;
    private int trait_id;

    public SightingTrait() {

    }
    public SightingTrait(int sighting_id, int trait_id) {
        this.sighting_id = sighting_id;
        this.trait_id = trait_id;
    }

    public int getSighting_id() {
        return sighting_id;
    }

    public void setSighting_id(int sighting_id) {
        this.sighting_id = sighting_id;
    }

    public int getTrait_id() {
        return trait_id;
    }

    public void setTrait_id(int trait_id) {
        this.trait_id = trait_id;
    }
}
