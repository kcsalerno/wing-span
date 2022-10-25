package learn.wing_span.data;

import learn.wing_span.models.Trait;

public interface TraitRepository {
    Trait findById(int traitId);
    Trait add(Trait trait);
    boolean update(Trait trait);
    boolean deleteById(int traitId);
}
