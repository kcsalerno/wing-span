package learn.wing_span.models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj)
    {
        // If this is the object, return true.
        if (this == obj)
            return true;
        // Check that object is not null, nor is the object's class different from the current runtime class,
        // if either is true, return false.
        if (obj == null || getClass() != obj.getClass())
            return false;
        // Cast the object's type.
        Trait trait = (Trait) obj;
        // Compare the fields between object and the forage.
        return (Objects.equals(name, trait.name));
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}
