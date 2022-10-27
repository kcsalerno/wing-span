package learn.wingspan.data;

import learn.wingspan.models.Avatar;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AvatarJdbcTemplateRepositoryTest {
    private final int AVATAR_COUNT = 2;

    @Autowired
    AvatarJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAvatarById() {
        Avatar actual = repository.findById(1);

        assertNotNull(actual);
        assertEquals(1, actual.getAvatarId());
        assertEquals("https://cdn.pixabay.com/photo/2017/05/25/21/26/bird-feeder-2344414__480.jpg", actual.getAvatarImageUrl());
    }

    @Test
    void shouldNotFindAvatarByBadId() {
        Avatar actual = repository.findById(99999);
        assertNull(actual);

        actual = repository.findById(0);
        assertNull(actual);
    }

    @Test
    void shouldAddAvatar() {
        Avatar avatar = makeAvatar();
        Avatar actual = repository.add(avatar);

        assertNotNull(actual);
        assertEquals("Bird Feeder", actual.getAvatarDescription());
        assertEquals(AVATAR_COUNT + 1, actual.getAvatarId());
    }

    @Test
    @Order(1)
    void shouldUpdateAvatar() {
        Avatar actual = repository.findById(2);
        actual.setAvatarDescription("updated");

        assertTrue(repository.update(actual));
        assertEquals("https://cdn.pixabay.com/photo/2022/09/29/08/39/european-robin-7486889__480.jpg", actual.getAvatarImageUrl());
        assertEquals("updated", actual.getAvatarDescription());
    }

    @Test
    @Order(2)
    void shouldDeleteById() {
        assertTrue(repository.deleteById(2));
        assertNull(repository.findById(2));
    }

    @Test
    void shouldNotDeleteByBadId() {
        assertFalse(repository.deleteById(0));
        assertFalse(repository.deleteById(9999999));
    }

    private Avatar makeAvatar() {
        Avatar avatar = new Avatar();
        avatar.setAvatarImageUrl("https://cdn.pixabay.com/photo/2017/05/25/21/26/bird-feeder.jpg");
        avatar.setAvatarDescription("Bird Feeder");
        return avatar;
    }
}