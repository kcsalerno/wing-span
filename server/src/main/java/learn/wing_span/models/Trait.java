package learn.wing_span.models;

public class Trait {
    private int traitId;
    private String name;

    public Trait(){

    }

    public Trait(int traitId, String name) {
        this.traitId = traitId;
        this.name = name;
    }

    public int getTraitId() {
        return traitId;
    }

    public void setTraitId(int traitId) {
        this.traitId = traitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
