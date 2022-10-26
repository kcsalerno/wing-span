package learn.wing_span.data;

import learn.wing_span.models.Bird;
import learn.wing_span.models.Sighting;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BirdJdbcTemplateRepositoryTest {
    @Autowired
    private BirdJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Bird> birds = repository.findAll();
        assertNotNull(birds);

        assertTrue(birds.size() >= 5);
    }

    @Test
    void shouldFindById() {
        Bird actual = repository.findById(3);

        assertNotNull(actual);
        assertEquals(3, actual.getBirdId());
        assertEquals("Peacock", actual.getCommonName());
        assertEquals("Pavo Cristatus", actual.getScientificName());
        assertEquals("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Peacock_Plumage.jpg/330px-Peacock_Plumage.jpg", actual.getBirdImageUrl());
    }

    @Test
    void shouldAdd() {
        Bird bird = makeBird();
        Bird actual = repository.add(bird);

        assertNotNull(actual);
    }

    @Test
    @Order(1)
    void shouldUpdate() {
        Bird bird = makeBird();
        bird.setBirdId(1);
        assertTrue(repository.update(bird));
    }

    @Test
    @Order(2)
    void shouldDelete() {
        assertTrue(repository.deleteById(1));
    }

    private Bird makeBird() {
        Bird bird = new Bird();
        bird.setBirdId(2);
        bird.setCommonName("Test Common Name");
        bird.setScientificName("Test Scientific Name");
        bird.setBirdImageUrl("Test/Url");
        return bird;
    }
}