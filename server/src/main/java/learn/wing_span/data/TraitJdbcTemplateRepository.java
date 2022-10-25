package learn.wing_span.data;

import learn.wing_span.models.Trait;

public class TraitJdbcTemplateRepository implements TraitRepository{
    @Override
    public Trait findById(int traitId) {
        return null;
    }

    @Override
    public Trait add(Trait trait) {
        return null;
    }

    @Override
    public boolean update(Trait trait) {
        return false;
    }

    @Override
    public boolean deleteById(int traitId) {
        return false;
    }
}
