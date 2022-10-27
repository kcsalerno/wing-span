package learn.wing_span.domain;

import learn.wing_span.data.AvatarJdbcTemplateRepository;
import learn.wing_span.models.Avatar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AvatarServiceTest {
    @MockBean
    AvatarJdbcTemplateRepository repository;

    @Autowired
    AvatarService service;

    @Test
    void shouldFindTwoAvatars() {
        when(repository.findAll()).thenReturn(List.of(
                new Avatar(1, "https://www.test.com", "Test Avatar"),
                new Avatar(2, "https://www.another.com", "Another Avatar")
        ));

        List<Avatar> avatars = service.findAll();

        assertEquals(2, avatars.size());
        assertEquals("Test Avatar", avatars.get(0).getAvatarDescription());
        assertEquals("Another Avatar", avatars.get(1).getAvatarDescription());
    }

    @Test
    void shouldFindAvatarWithIdOfTwo() {
        when(repository.findById(1)).thenReturn(new Avatar(1, "https://www.test.com",
                "Test Avatar"));

        Avatar result = service.findById(1);

        assertNotNull(result);
        assertEquals("Test Avatar", result.getAvatarDescription());
    }

    @Test
    void shouldNotFindAvatarWithBadId() {
        when(repository.findById(1)).thenReturn(new Avatar(1, "https://www.test.com",
                "Test Avatar"));

        Avatar result = service.findById(99999);
        assertNull(result);

        result = service.findById(0);
        assertNull(result);
    }

    @Test
    void shouldAddAvatar() {
        Avatar avatar = new Avatar(0, "https://www.test.com", "Test Avatar");

        Result<Avatar> result = service.add(avatar);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddNullAvatar() {
        Result<Avatar> result = service.add(null);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("cannot be null"));
    }

    @Test
    void shouldNotAddWithNullOrBlankAvatarDescription() {
        Avatar avatar = new Avatar(1, "https://www.test.com", null);

        Result<Avatar> result = service.add(avatar);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("description is required"));

        avatar = new Avatar(1, "https://www.test.com", "");

        result = service.add(avatar);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("description is required"));
    }

    @Test
    void shouldNotAddWithNullOrBlankImgUrl() {
        Avatar avatar = new Avatar(1, null, "Test Avatar");

        Result<Avatar> result = service.add(avatar);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("url is required"));

        avatar = new Avatar(1, "", "Test Avatar");

        result = service.add(avatar);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("url is required"));
    }

    @Test
    void shouldNotAddWithPositiveId() {
        Avatar avatar = new Avatar(9999999, "https://www.test.com", "Test Avatar");

        Result<Avatar> result = service.add(avatar);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("ID cannot be set for `add`"));
    }

    @Test
    void shouldNotAddDuplicateAvatar() {
        Avatar avatar = new Avatar(0, "https://www.test.com", "Test Avatar");

        when(repository.findAll()).thenReturn(List.of(
                new Avatar(1, "https://www.test.com", "Test Avatar")
        ));

        Result<Avatar> result = service.add(avatar);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("cannot be duplicated"));
    }

    @Test
    void shouldUpdateAvatar() {
        Avatar avatar = new Avatar(1, "https://www.test.com", "Test Avatar");

        when(repository.update(avatar)).thenReturn(true);
        Result<Avatar> result = service.update(avatar);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateNullAvatar() {
        Result<Avatar> result = service.update(null);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("cannot be null"));
    }

    @Test
    void shouldNotUpdateWithNullOrBlankAvatarDescription() {
        Avatar avatar = new Avatar(1, "https://www.test.com", null);

        Result<Avatar> result = service.update(avatar);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("description is required"));

        avatar = new Avatar(1, "https://www.test.com", "");

        result = service.update(avatar);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("description is required"));
    }

    @Test
    void shouldNotUpdateWithNullOrBlankImgUrl() {
        Avatar avatar = new Avatar(1, null, "Test Avatar");

        Result<Avatar> result = service.update(avatar);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("url is required"));

        avatar = new Avatar(1, "", "Test Avatar");

        result = service.update(avatar);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("url is required"));
    }

    @Test
    void shouldNotUpdateWithoutPositiveId() {
        Avatar avatar = new Avatar(0, "https://www.test.com", "Test Avatar");

        Result<Avatar> result = service.update(avatar);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("ID must be set for `update`"));
    }

    @Test
    void shouldNotUpdateDuplicateAvatar() {
        Avatar avatar = new Avatar(0, "https://www.test.com", "Test Avatar");

        when(repository.findAll()).thenReturn(List.of(
                new Avatar(1, "https://www.test.com", "Test Avatar")
        ));

        Result<Avatar> result = service.update(avatar);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("cannot be duplicated"));
    }

    @Test
    void shouldDeleteById() {
        when(repository.deleteById(2)).thenReturn(true);
        Result<Avatar> result = service.deleteById(2);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotDeleteNonExistingAvatar() {
        Result<Avatar> result = service.deleteById(9999);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Avatar ID not found.", result.getMessages().get(0));
    }
}