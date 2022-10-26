package learn.wing_span.data;

import learn.wing_span.models.Trait;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TraitJdbcTemplateRepositoryTest {
    @Autowired
    TraitJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Trait> traits = repository.findAll();

        assertNotNull(traits);
        assertTrue(traits.size() >= 9);
    }

    @Test
    void shouldFindById() {
        Trait actual = repository.findById(1);

        assertNotNull(actual);
        assertEquals(1, actual.getTraitId());
        assertEquals("Perching", actual.getName());
    }

    @Test
    void shouldAdd() {
        Trait trait = makeTrait();
        Trait actual = repository.add(trait);

        assertNotNull(actual);
        assertEquals(10, actual.getTraitId());
    }

    @Test
    @Order(1)
    void shouldUpdate() {
        Trait trait = makeTrait();
        trait.setTraitId(6);

        assertTrue(repository.update(trait));
    }

    @Test
    @Order(2)
    void shouldDelete() {
        assertTrue(repository.deleteById(6));
    }

    private Trait makeTrait() {
        Trait trait = new Trait();
        trait.setName("Mocking");
        trait.setTraitId(5);
        return trait;
    }
}