package learn.wing_span.data;

import learn.wing_span.models.Bird;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BirdJdbcTemplateRepositoryTest {
    private final int BIRD_COUNT = 5;

    @Autowired
    KnownGoodState knownGoodState;

    @Autowired
    BirdJdbcTemplateRepository repository;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllBirds() {
        List<Bird> birds = repository.findAll();
        assertNotNull(birds);

        assertTrue(birds.size() >= BIRD_COUNT || birds.size() == BIRD_COUNT - 1) ;
    }

    @Test
    void shouldFindBirdById() {
        Bird actual = repository.findById(1);

        assertNotNull(actual);
        assertEquals(1, actual.getBirdId());
        assertEquals("Woodpecker", actual.getCommonName());
        assertEquals("Picidae", actual.getScientificName());
        assertEquals("https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Woodpecker_20040529_151837_1c_cropped.JPG/330px-Woodpecker_20040529_151837_1c_cropped.JPG",
                actual.getBirdImageUrl());
    }

    @Test
    void shouldNotFindBirdByBadId() {
        Bird actual = repository.findById(99999);
        assertNull(actual);

        actual = repository.findById(0);
        assertNull(actual);
    }

    @Test
    void shouldAddBird() {
        Bird bird = makeBird();
        Bird actual = repository.add(bird);

        assertNotNull(actual);
        assertEquals("Test Common Name", actual.getCommonName());
        assertEquals(BIRD_COUNT + 1, actual.getBirdId());
    }

    @Test
    void shouldUpdateBird() {
        Bird actual = repository.findById(2);
        actual.setCommonName("updated");

        assertTrue(repository.update(actual));
        assertEquals("Columbidae", actual.getScientificName());
        assertEquals("updated", actual.getCommonName());
    }

    @Test
    void shouldDeleteById() {
        assertTrue(repository.deleteById(5));
        assertNull(repository.findById(5));
    }

    @Test
    void shouldNotDeleteByBadId() {
        assertFalse(repository.deleteById(0));
        assertFalse(repository.deleteById(9999999));
    }

    private Bird makeBird() {
        Bird bird = new Bird();
        bird.setCommonName("Test Common Name");
        bird.setScientificName("Test Scientific Name");
        bird.setBirdImageUrl("Test/Url");
        return bird;
    }
}