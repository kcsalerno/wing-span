package learn.wingspan.domain;

import learn.wingspan.data.BadgeJdbcTemplateRepository;
import learn.wingspan.models.Badge;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BadgeServiceTest {

    @MockBean
    BadgeJdbcTemplateRepository repository;

    @Autowired
    BadgeService service;

    @Test
    void shouldFindTwoBadges() {
        when(repository.findAll()).thenReturn(List.of(
                new Badge(1, "Test", "Test Badge", "test.url"),
                new Badge(2, "Test Two", "Test Badge Two", "testtwo.url")
        ));

        List<Badge> badges = service.findAll();
        assertEquals(2, badges.size());
    }

    @Test
    void shouldFindBadgeWithAnIdOfTwo() {
        when(repository.findById(2)).thenReturn(new Badge());
        Badge result = service.findById(2);

        assertNotNull(result);
    }

    @Test
    void shouldNotAddWithNullName() {
        Badge badge = new Badge();
        badge.setBadgeName(null);
        badge.setBadgeDescription("Test description");
        badge.setBadgeImgUrl("test.url");
        Result<Badge> result = service.add(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A badge name is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithBlankName() {
        Badge badge = new Badge();
        badge.setBadgeName(" ");
        badge.setBadgeDescription("Test description");
        badge.setBadgeImgUrl("test.url");
        Result<Badge> result = service.add(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A badge name is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithDuplicateName() {
        Badge badge = new Badge();
        badge.setBadgeName("Test");
        badge.setBadgeDescription("Test description");
        badge.setBadgeImgUrl("test.url");

        when(repository.findAll()).thenReturn(List.of(
                new Badge(1, "Test", "test", "test")
        ));
        Result<Badge> result = service.add(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Badge name cannot be duplicated.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithNullDescription() {
        Badge badge = new Badge();
        badge.setBadgeName("Test");
        badge.setBadgeDescription(null);
        badge.setBadgeImgUrl("test.url");
        Result<Badge> result = service.add(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A badge description is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithBlankDescription() {
        Badge badge = new Badge();
        badge.setBadgeName("Test");
        badge.setBadgeDescription(" ");
        badge.setBadgeImgUrl("test.url");
        Result<Badge> result = service.add(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A badge description is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithDuplicateDescription() {
        Badge badge = new Badge();
        badge.setBadgeName("Test Name");
        badge.setBadgeDescription("Test");
        badge.setBadgeImgUrl("test.url");

        when(repository.findAll()).thenReturn(List.of(
                new Badge(1, "Test", "test", "test")
        ));
        Result<Badge> result = service.add(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Badge description cannot be duplicated.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithNullImage() {
        Badge badge = new Badge();
        badge.setBadgeName("Test");
        badge.setBadgeDescription("Test description");
        badge.setBadgeImgUrl(null);
        Result<Badge> result = service.add(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A badge image is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithBlankImage() {
        Badge badge = new Badge();
        badge.setBadgeName("Test");
        badge.setBadgeDescription("Test description");
        badge.setBadgeImgUrl(" ");
        Result<Badge> result = service.add(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A badge image is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithDuplicateImage() {
        Badge badge = new Badge();
        badge.setBadgeName("Test Name");
        badge.setBadgeDescription("Test description");
        badge.setBadgeImgUrl("test.url");

        when(repository.findAll()).thenReturn(List.of(
                new Badge(1, "Test", "test", "test.url")
        ));
        Result<Badge> result = service.add(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Badge image cannot be duplicated.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithPositiveId() {
        Badge badge = new Badge();
        badge.setBadgeId(20);
        badge.setBadgeName("Test Name");
        badge.setBadgeDescription("Test description");
        badge.setBadgeImgUrl("test.url");
        Result<Badge> result = service.add(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Badge ID cannot be set for `add` operation.", result.getMessages().get(0));
    }

    @Test
    void shouldAdd() {
        Badge badge = new Badge();
        badge.setBadgeName("Test Name");
        badge.setBadgeDescription("Test description");
        badge.setBadgeImgUrl("test.url");
        Result<Badge> result = service.add(badge);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateWithNullName() {
        Badge badge = new Badge(1, null, "Test description", "test.url");

        Result<Badge> result = service.update(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A badge name is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateWithBlankName() {
        Badge badge = new Badge(1, " ", "Test description", "test.url");

        Result<Badge> result = service.update(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A badge name is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateWithDuplicateName() {
        Badge badge = new Badge(2, "Test", "Test description", "test.url.test");

        when(repository.findAll()).thenReturn(List.of(
                new Badge(1, "Test", "Test Badge", "test.url"),
                new Badge(2, "Test Two", "Test Badge Two", "testtwo.url")
        ));
        Result<Badge> result = service.update(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Badge name cannot be duplicated.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateWithNullDescription() {
        Badge badge = new Badge(1, "Test Name", null, "test.url");

        Result<Badge> result = service.update(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A badge description is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateWithBlankDescription() {
        Badge badge = new Badge(1, "Test Name", " ", "test.url");

        Result<Badge> result = service.update(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A badge description is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateWithDuplicateDescription() {
        Badge badge = new Badge(2, "Test Name", "Test Badge", "test.url.test");

        when(repository.findAll()).thenReturn(List.of(
                new Badge(1, "Test", "Test Badge", "test.url"),
                new Badge(2, "Test Two", "Test Badge Two", "testtwo.url")
        ));
        Result<Badge> result = service.update(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Badge description cannot be duplicated.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateWithNullImage() {
        Badge badge = new Badge(1, "Test Name", "test description", null);

        Result<Badge> result = service.update(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A badge image is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateWithBlankImage() {
        Badge badge = new Badge(1, "Test Name", "test description", " ");

        Result<Badge> result = service.update(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("A badge image is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateWithDuplicateImage() {
        Badge badge = new Badge(2, "Test Name", "Test description", "test.url");

        when(repository.findAll()).thenReturn(List.of(
                new Badge(1, "Test", "Test Badge", "test.url"),
                new Badge(2, "Test Two", "Test Badge Two", "testtwo.url")
        ));
        Result<Badge> result = service.update(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Badge image cannot be duplicated.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateWithNonPositiveId() {
        Badge badge = new Badge();
        badge.setBadgeName("Test Name");
        badge.setBadgeDescription("Test description");
        badge.setBadgeImgUrl("test.url");

        Result<Badge> result = service.update(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Badge ID must be set for `update` operation.", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateNonExistingBadge() {
        Badge badge = new Badge(9999, "Test Name", "Test description", "test.url");

        Result<Badge> result = service.update(badge);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Badge ID not found.", result.getMessages().get(0));
    }

    @Test
    void shouldUpdate() {
        Badge badge = new Badge(1, "Test Name", "Test description", "test.url");

        when(repository.update(badge)).thenReturn(true);
        Result<Badge> result = service.update(badge);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotDeleteNonExistingBadge() {
        Result<Badge> result = service.deleteById(9999);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Badge ID not found.", result.getMessages().get(0));
    }

    @Test
    void shouldDeleteBadge() {
        when(repository.deleteById(2)).thenReturn(true);
        Result<Badge> result = service.deleteById(2);

        assertTrue(result.isSuccess());
    }
}