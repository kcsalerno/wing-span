package learn.wingspan.data;

import learn.wingspan.models.Trait;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TraitJdbcTemplateRepositoryTest {
    private final int TRAIT_COUNT = 9;

    @Autowired
    TraitJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllTraits() {
        List<Trait> traits = repository.findAll();

        assertNotNull(traits);
        assertTrue(traits.size() >= TRAIT_COUNT || traits.size() == TRAIT_COUNT - 1);
    }

    @Test
    void shouldFindTraitById() {
        Trait actual = repository.findById(1);

        assertNotNull(actual);
        assertEquals(1, actual.getTraitId());
        assertEquals("Perching", actual.getName());
    }

    @Test
    void shouldNotFindTraitByBadId() {
        Trait actual = repository.findById(99999);
        assertNull(actual);

        actual = repository.findById(0);
        assertNull(actual);
    }

    @Test
    void shouldAddTrait() {
        Trait trait = makeTrait();
        Trait actual = repository.add(trait);

        assertNotNull(actual);
        assertEquals("Mocking", actual.getName());
        assertEquals(TRAIT_COUNT + 1, actual.getTraitId());
    }

    @Test
    void shouldUpdateTrait() {
        Trait actual = repository.findById(2);
        actual.setName("updated");

        assertTrue(repository.update(actual));
        assertEquals("updated", actual.getName());
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(9));
        assertNull(repository.findById(9));
    }

    @Test
    void shouldNotDeleteByBadId() {
        assertFalse(repository.deleteById(0));
        assertFalse(repository.deleteById(9999999));
    }

    private Trait makeTrait() {
        Trait trait = new Trait();
        trait.setName("Mocking");
        trait.setTraitId(5);
        return trait;
    }
}