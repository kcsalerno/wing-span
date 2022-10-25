package learn.wing_span.data;

import learn.wing_span.models.Sighting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SightingJdbcTemplateRepositoryTest {
    @Autowired
    private SightingJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Sighting> sightings = repository.findAll();
        assertNotNull(sightings);

        assertTrue(sightings.size() >= 5);
    }

    @Test
    void shouldFindById() {
        Sighting actual = repository.findById(1);
        assertNotNull(actual);
        assertEquals(1, actual.getSightingId());
        assertEquals("Test City", actual.getCity());
    }

    @Test
    void shouldAdd() {
        Sighting sighting = makeSighting();
        Sighting actual = repository.create(sighting);
        assertNotNull(actual);
    }


    @Test
    void shouldUpdate() {
        Sighting sighting = makeSighting();
        sighting.setSightingId(2);
        assertTrue(repository.update(sighting));
        assertEquals(sighting, repository.findById(2));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteByKey(1, 1,2));
    }

    private Sighting makeSighting() {
        Sighting sighting = new Sighting();
        sighting.setSightingUserId(5);
        sighting.setSightingBirdId(5);
        sighting.setDate(LocalDate.of(2020, 6, 12));
        sighting.setCity("Test City");
        sighting.setState("Test State");
        sighting.setDaytime(true);
        return sighting;
    }
}