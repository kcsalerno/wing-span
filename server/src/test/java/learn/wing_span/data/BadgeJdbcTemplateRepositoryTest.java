package learn.wing_span.data;

import learn.wing_span.models.Avatar;
import learn.wing_span.models.Badge;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BadgeJdbcTemplateRepositoryTest {
    @Autowired
    BadgeJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        Badge actual= repository.findById(1);

        assertNotNull(actual);
        assertEquals(1, actual.getBadgeId());
        assertEquals("5 Sightings", actual.getBadgeName());
        assertEquals("You've made 5 sightings!", actual.getBadgeDescription());
        assertEquals("https://static.thenounproject.com/png/1120113-200.png", actual.getBadgeImgUrl());
    }

    @Test
    void shouldAdd() {
        Badge badge = makeBadge();
        Badge actual = repository.add(badge);
        assertNotNull(actual);
        assertEquals(3, actual.getBadgeId());
    }

    @Test
    @Order(1)
    void shouldUpdate() {
        Badge badge = makeBadge();
        badge.setBadgeId(2);

        assertTrue(repository.update(badge));
    }

    @Test
    @Order(2)
    void shouldDelete() {
        assertTrue(repository.deleteById(2));
    }

    private Badge makeBadge() {
        Badge badge = new Badge();
        badge.setBadgeName("Test Badge name");
        badge.setBadgeDescription("Test Badge description");
        badge.setBadgeImgUrl("https://static.thenounproject.com/png/1188264-200.png");
        badge.setBadgeId(3);
        return badge;
    }
}