package learn.wingspan.domain;

import learn.wingspan.data.BirdJdbcTemplateRepository;
import learn.wingspan.models.Bird;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BirdServiceTest {

    @MockBean
    BirdJdbcTemplateRepository repository;

    @Autowired
    BirdService service;

    @Test
    void shouldFindTwoBirds() {
        when(repository.findAll()).thenReturn(List.of(
                new Bird(1, "Test Bird", "Testimus Maximus",
                        "https://www.testing.com"),
                new Bird(2, "Another Bird", "Otherum Birdium",
                        "https://www.another.com")
        ));

        List<Bird> birds = service.findAll();
        assertEquals(2, birds.size());
        assertEquals("Test Bird", birds.get(0).getCommonName());
        assertEquals("Another Bird", birds.get(1).getCommonName());
    }

    @Test
    void shouldFindBirdWithIdOfTwo() {
        when(repository.findById(1)).thenReturn(new Bird(1, "Test Bird",
                "Testimus Maximus", "https://www.testing.com"));

        Bird result = service.findById(1);

        assertNotNull(result);
        assertEquals("Test Bird", result.getCommonName());
    }

    @Test
    void shouldNotFindBirdWithBadId() {
        when(repository.findById(1)).thenReturn(new Bird(1, "Test Bird",
                "Testimus Maximus", "https://www.testing.com"));

        Bird result = service.findById(99999);
        assertNull(result);

        result = service.findById(0);
        assertNull(result);
    }

    @Test
    void shouldAddBird() {
        Bird bird = new Bird(0, "Test Bird", "Testimus Maximus",
                "https://www.testing.com");

        Result<Bird> result = service.add(bird);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddNullBird() {
        Result<Bird> result = service.add(null);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("cannot be null"));
    }

    @Test
    void shouldNotAddWithNullOrBlankCommonName() {
        Bird bird = new Bird(1, null, "Testimus Maximus",
                "https://www.testing.com");

        Result<Bird> result = service.add(bird);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("common name is required"));

        bird = new Bird(1, "", "Testimus Maximus", "https://www.testing.com");

        result = service.add(bird);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("common name is required"));
    }

    @Test
    void shouldNotAddWithNullOrBlankScientificName() {
        Bird bird = new Bird(1, "Test Bird", null,
                "https://www.testing.com");


        Result<Bird> result = service.add(bird);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("scientific name is required"));

        bird = new Bird(1, "Test Bird", "", "https://www.testing.com");

        result = service.add(bird);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("scientific name is required"));
    }

    @Test
    void shouldNotAddWithPositiveId() {
        Bird bird = new Bird(99999, "Test Bird", "Testimus Maximus",
                "https://www.testing.com");

        Result<Bird> result = service.add(bird);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("ID cannot be set for `add`"));
    }

    @Test
    void shouldNotAddDuplicateBird() {
        Bird bird = new Bird(0, "Test Bird", "Testimus Maximus",
                "https://www.testing.com");

        when(repository.findAll()).thenReturn(List.of(
                new Bird(1, "Test Bird", "Testimus Maximus",
                        "https://www.testing.com")
        ));

        Result<Bird> result = service.add(bird);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("cannot be duplicated"));
    }

    @Test
    void shouldUpdate() {
        Bird bird = new Bird(1, "Test Bird", "Testimus Maximus",
                "https://www.testing.com");

        when(repository.update(bird)).thenReturn(true);
        Result<Bird> result = service.update(bird);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateNullBird() {
        Result<Bird> result = service.update(null);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("cannot be null"));
    }

    @Test
    void shouldNotUpdateWithNullOrBlankCommonName() {
        Bird bird = new Bird(1, null, "Testimus Maximus",
                "https://www.testing.com");

        Result<Bird> result = service.update(bird);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("common name is required"));

        bird = new Bird(1, "", "Testimus Maximus", "https://www.testing.com");

        result = service.update(bird);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("common name is required"));
    }

    @Test
    void shouldNotUpdateWithNullOrBlankScientificName() {
        Bird bird = new Bird(1, "Test Bird", null,
                "https://www.testing.com");


        Result<Bird> result = service.update(bird);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("scientific name is required"));

        bird = new Bird(1, "Test Bird", "", "https://www.testing.com");

        result = service.update(bird);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("scientific name is required"));
    }

    @Test
    void shouldNotUpdateWithoutPositiveId() {
        Bird bird = new Bird(0, "Test Bird", "Testimus Maximus",
                "https://www.testing.com");

        Result<Bird> result = service.update(bird);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("ID must be set for `update`"));
    }

    @Test
    void shouldNotUpdateDuplicateBird() {
        Bird bird = new Bird(0, "Test Bird", "Testimus Maximus",
                "https://www.testing.com");

        when(repository.findAll()).thenReturn(List.of(
                new Bird(1, "Test Bird", "Testimus Maximus",
                        "https://www.testing.com")
        ));

        Result<Bird> result = service.update(bird);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("cannot be duplicated"));
    }

    @Test
    void shouldDeleteById() {
        when(repository.deleteById(2)).thenReturn(true);
        Result<Bird> result = service.deleteById(2);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotDeleteNonExistingBird() {
        Result<Bird> result = service.deleteById(9999);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Bird ID not found.", result.getMessages().get(0));
    }
}