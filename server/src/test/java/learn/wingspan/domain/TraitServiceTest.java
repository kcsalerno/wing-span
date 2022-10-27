package learn.wingspan.domain;

import learn.wingspan.data.TraitJdbcTemplateRepository;
import learn.wingspan.models.Trait;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TraitServiceTest {

    @MockBean
    TraitJdbcTemplateRepository repository;

    @Autowired
    TraitService service;

    @Test
    void shouldFindTwoTraits() {
        when(repository.findAll()).thenReturn(List.of(
                new Trait(1, "Flying"),
                new Trait(2, "Nesting")
        ));

        List<Trait> traits = service.findAll();
        assertEquals(2, traits.size());
    }

    @Test
    void shouldFindTraitWithAnIdOfTwo() {
        when(repository.findById(2)).thenReturn(new Trait());
        Trait result = service.findById(2);

        assertNotNull(result);
    }

    @Test
    void shouldNotAddWithNullName() {
        Trait trait = new Trait();
        trait.setName(null);
        Result<Trait> result = service.add(trait);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A trait name is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithBlankName() {
        Trait trait = new Trait();
        trait.setName(" ");
        Result<Trait> result = service.add(trait);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A trait name is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithDuplicateName() {
        Trait trait = new Trait();
        trait.setName("Flying");

        when(repository.findAll()).thenReturn(List.of(
                new Trait(1, "Flying")
        ));
        Result<Trait> result = service.add(trait);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Trait name cannot be duplicated.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithPositiveId() {
        Trait trait = new Trait();
        trait.setTraitId(15);
        trait.setName("Test");

        Result<Trait> result = service.add(trait);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Trait ID cannot be set for `add` operation.", result.getMessages().get(0));
    }

    @Test
    void shouldAdd() {
        Trait trait = new Trait();
        trait.setName("Hunting");

        Result<Trait> result = service.add(trait);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateWithNullName() {
        Trait trait = new Trait(1, null);

        Result<Trait> result = service.update(trait);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A trait name is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateWithBlankName() {
        Trait trait = new Trait(1, " ");

        Result<Trait> result = service.update(trait);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A trait name is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateWithDuplicateName() {
        Trait trait = new Trait();
        trait.setTraitId(1);
        trait.setName("Flying");

        when(repository.findAll()).thenReturn(List.of(
                new Trait(1, "Nesting"),
                new Trait(2, "Flying")
        ));
        Result<Trait> result = service.update(trait);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Trait name cannot be duplicated.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateWithNonPositiveId() {
        Trait trait = new Trait();
        trait.setName("Test");

        Result<Trait> result = service.update(trait);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Trait ID must be set for `update` operation.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateNonExistingTrait() {
        Trait trait = new Trait(9999, "Test");

        Result<Trait> result = service.update(trait);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Trait ID not found.", result.getMessages().get(0));
    }

    @Test
    void shouldUpdate() {
        Trait trait = new Trait(1, "Eating");

        when(repository.update(trait)).thenReturn(true);
        Result<Trait> result = service.update(trait);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotDeleteNonExistingTrait() {
        Result<Trait> result = service.deleteById(9999);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Trait ID not found.", result.getMessages().get(0));
    }

    @Test
    void shouldDelete() {
        when(repository.deleteById(2)).thenReturn(true);
        Result<Trait> result = service.deleteById(2);

        assertTrue(result.isSuccess());
    }
}