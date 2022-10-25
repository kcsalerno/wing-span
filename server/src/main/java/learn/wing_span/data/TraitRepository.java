package learn.wing_span.data;

import learn.wing_span.models.Trait;

import java.util.List;

public interface TraitRepository {
    List<Trait> findAll();
    Trait findById(int traitId);
    Trait add(Trait trait);
    boolean update(Trait trait);
    boolean deleteById(int traitId);
}
