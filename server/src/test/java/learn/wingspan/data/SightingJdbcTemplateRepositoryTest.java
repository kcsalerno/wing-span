package learn.wingspan.data;

import learn.wingspan.models.Sighting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SightingJdbcTemplateRepositoryTest {
    private final int SIGHTING_COUNT = 5;

    @Autowired
    KnownGoodState knownGoodState;

    @Autowired
    SightingJdbcTemplateRepository repository;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllSightings() {
        List<Sighting> sightings = repository.findAll();
        assertNotNull(sightings);

        assertTrue(sightings.size() >= SIGHTING_COUNT || sightings.size() == SIGHTING_COUNT - 1);
    }

    @Test
    void shouldFindSightingById() {
        Sighting actual = repository.findById(1);

        assertNotNull(actual);
        assertEquals(1, actual.getSightingId());
        assertEquals("Test City", actual.getCity());
        assertEquals("Test State", actual.getState());
    }

    @Test
    void shouldNotFindSightingByBadId() {
        Sighting actual = repository.findById(99999);
        assertNull(actual);

        actual = repository.findById(0);
        assertNull(actual);
    }

    @Test
    void shouldAddSighting() {
        Sighting sighting = makeSighting();
        Sighting actual = repository.create(sighting);

        assertNotNull(actual);
        assertEquals("2020-06-12", actual.getDate().toString());
        assertTrue(actual.isDaytime());
        assertEquals(SIGHTING_COUNT + 1, actual.getSightingId());
    }

    @Test
    void shouldUpdateSighting() {
        Sighting actual = repository.findById(2);
        actual.setCity("updated");

        assertTrue(repository.update(actual));
        assertEquals("2021-11-01", actual.getDate().toString());
        assertEquals("updated", actual.getCity());
    }

    @Test
    void shouldDeleteById() {
        assertTrue(repository.deleteById(4));
        assertNull(repository.findById(4));
    }

    @Test
    void shouldNotDeleteByBadId() {
        assertFalse(repository.deleteById(0));
        assertFalse(repository.deleteById(9999999));
    }

    private Sighting makeSighting() {
        Sighting sighting = new Sighting();
        sighting.setSightingUserId(1);
        sighting.setSightingBirdId(1);
        sighting.setDate(LocalDate.of(2020, 6, 12));
        sighting.setCity("Test City");
        sighting.setState("Test State");
        sighting.setDaytime(true);
        return sighting;
    }
}