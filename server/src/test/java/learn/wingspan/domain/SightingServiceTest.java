package learn.wingspan.domain;


import learn.wingspan.data.SightingJdbcTemplateRepository;
import learn.wingspan.models.Sighting;
import learn.wingspan.models.Trait;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SightingServiceTest {

    @MockBean
    SightingJdbcTemplateRepository repository;

    @Autowired
    SightingService service;

    @Test
    void shouldFindAllSightings() {
        when(repository.findAll())
                .thenReturn(List.of(
                        new Sighting(1, 1, 1, LocalDate.of(2020, 10, 1), "Test City", "Test State", true),
                        new Sighting(2, 1, 2, LocalDate.of(2021, 11, 1), "Test City", "Test State", true),
                        new Sighting(3, 1, 3, LocalDate.of(2021, 12, 1), "Test City", "Test State", false),
                        new Sighting(4, 1, 4, LocalDate.of(2022, 4, 1), "Test City", "Test State", false),
                        new Sighting(5, 1, 5, LocalDate.of(2022, 6, 1), "Test City", "Test State", true)
                ));

        List<Sighting> sightings = service.findAll();
        assertEquals(5, sightings.size());
    }

    @Test
    void shouldFindSightingByIdOne() {
        when(repository.findById(1)).thenReturn(new Sighting());
        Sighting result = service.findById(1);

        assertNotNull(result);
    }

    @Test
    void shouldCreateSighting() {
        Sighting sighting = makeSighting();
        Sighting mockOut = makeSighting();

        when(repository.create(sighting)).thenReturn(mockOut);

        Result<Sighting> actual = service.create(sighting);

        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotCreateNullSighting() {
        Result<Sighting> result = service.create(null);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotCreateWithInvalidUserId() {
        Sighting sighting = makeSighting();
        sighting.setSightingUserId(0);
        Result<Sighting> result = service.create(sighting);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotCreateWithInvalidBirdId() {
        Sighting sighting = makeSighting();
        sighting.setSightingBirdId(0);

        Result<Sighting> result = service.create(sighting);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotCreateWithNullDate() {
        Sighting sighting = makeSighting();
        sighting.setDate(null);

        Result<Sighting> result = service.create(sighting);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotCreateWithInvalidDate() {
        Sighting sighting = makeSighting();
        sighting.setDate(LocalDate.of(2022, 12, 15));

        Result<Sighting> result = service.create(sighting);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotCreateWithNullCity() {
        Sighting sighting = makeSighting();
        sighting.setCity(null);

        Result<Sighting> result = service.create(sighting);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotCreateDuplicateSighting() {
        Sighting sighting = makeSighting();
        sighting.setSightingUserId(1);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(makeSighting());

        when(repository.findAll()).thenReturn(sightings);

        Result<Sighting> result = service.create(sighting);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Sighting cannot be duplicated", result.getMessages().get(0));
    }

    @Test
    void shouldUpdateSighting() {
        Sighting sighting = makeSighting();
        Sighting mockOut = makeSighting();
        mockOut.setSightingId(1);

        when(repository.create(sighting)).thenReturn(mockOut);

        Result<Sighting> actual = service.create(sighting);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotUpdateWithInvalidUserId() {
        Sighting sighting = makeSighting();
        sighting.setSightingUserId(0);
        Result<Sighting> actual = service.update(sighting);

        assertFalse(actual.isSuccess());
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotUpdateWithInvalidBirdId() {
        Sighting sighting = makeSighting();
        sighting.setSightingBirdId(0);
        Result<Sighting> result = service.update(sighting);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWithNullDate() {
        Sighting sighting = makeSighting();
        sighting.setDate(LocalDate.of(2022, 12, 15));
        Result<Sighting> result = service.update(sighting);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWithInvalidDate() {
        Sighting sighting = makeSighting();
        sighting.setDate(LocalDate.of(2022, 12, 15));
        Result<Sighting> result = service.update(sighting);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWithNullCity() {
        Sighting sighting = makeSighting();
        sighting.setCity(null);
        Result<Sighting> result = service.update(sighting);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateDuplicateSighting() {
        Sighting sighting = makeSighting();
        sighting.setSightingUserId(1);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(makeSighting());

        when(repository.findAll()).thenReturn(sightings);

        Result<Sighting> result = service.update(sighting);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Sighting cannot be duplicated", result.getMessages().get(0));
    }

    @Test
    void shouldDeleteSighting() {
        when(repository.deleteById(1)).thenReturn(true);
        Result<Sighting> result = service.deleteById(1);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotDeleteNonExistentSighting() {
        Result<Sighting> result = service.deleteById(20);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Sighting ID not found", result.getMessages().get(0));
    }

    Sighting makeSighting() {
        Sighting sighting = new Sighting();
        Trait firstTrait = new Trait(1, "Perching");
        Trait secondTrait = new Trait(2, "Hopping");
        List<Trait> traits = new ArrayList<>();
        traits.add(firstTrait);
        traits.add(secondTrait);

        sighting.setSightingUserId(1);
        sighting.setSightingBirdId(2);
        sighting.setDate(LocalDate.of(2020, 3, 10));
        sighting.setCity("Baltimore");
        sighting.setState("Maryland");
        sighting.setDaytime(false);
        sighting.setTraits(traits);

        return sighting;
    }
}