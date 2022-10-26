package learn.wing_span.data;

import learn.wing_span.models.Badge;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BadgeJdbcTemplateRepositoryTest {
    private final int BADGE_COUNT = 2;

    @Autowired
    BadgeJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllBadges() {
        List<Badge> badges = repository.findAll();

        assertNotNull(badges);
        assertTrue(badges.size() >= 1 && badges.size() <= 3);
    }

    @Test
    void shouldFindBadgeById() {
        Badge actual = repository.findById(1);

        assertNotNull(actual);
        assertEquals(1, actual.getBadgeId());
        assertEquals("5 Sightings", actual.getBadgeName());
        assertEquals("You've made 5 sightings!", actual.getBadgeDescription());
        assertEquals("https://static.thenounproject.com/png/1120113-200.png", actual.getBadgeImgUrl());
    }

    @Test
    void shouldNotFindBadgeByBadId() {
        Badge actual = repository.findById(0);
        assertNull(actual);

        actual = repository.findById(999999);
        assertNull(actual);
    }

    @Test
    void shouldAddBadge() {
        Badge badge = makeBadge();
        Badge actual = repository.add(badge);

        assertNotNull(actual);
        assertEquals("Test Badge name", actual.getBadgeName());
        assertEquals(BADGE_COUNT + 1, actual.getBadgeId());
    }

    @Test
    @Order(1)
    void shouldUpdateBadge() {
        Badge actual = repository.findById(2);
        actual.setBadgeDescription("updated");

        assertTrue(repository.update(actual));
        assertEquals("10 Sightings", actual.getBadgeName());
        assertEquals("updated", actual.getBadgeDescription());
    }

    @Test
    @Order(2)
    void shouldDeleteById() {
        assertTrue(repository.deleteById(2));
        assertNull(repository.findById(2));
    }

    @Test
    void shouldNotDeleteByBadId () {
        assertFalse(repository.deleteById(0));
        assertFalse(repository.deleteById(9999999));
    }

    private Badge makeBadge() {
        Badge badge = new Badge();
        badge.setBadgeName("Test Badge name");
        badge.setBadgeDescription("Test Badge description");
        badge.setBadgeImgUrl("https://static.thenounproject.com/png/1188264-200.png");
        return badge;
    }
}