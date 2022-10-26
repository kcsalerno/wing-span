package learn.wing_span.data;

import learn.wing_span.models.Avatar;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AvatarJdbcTemplateRepositoryTest {
    @Autowired
    AvatarJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        Avatar actual = repository.findById(1);

        assertNotNull(actual);
        assertEquals(1, actual.getAvatarId());
        assertEquals("https://cdn.pixabay.com/photo/2017/05/25/21/26/bird-feeder-2344414__480.jpg", actual.getAvatarImageUrl());
    }

    @Test
    void shouldAdd() {
        Avatar avatar = makeAvatar();
        Avatar actual = repository.add(avatar);
        assertNotNull(actual);
        assertEquals(3, actual.getAvatarId());
    }

    @Test
    @Order(1)
    void shouldUpdate() {
        Avatar avatar = makeAvatar();
        avatar.setAvatarId(2);
        assertTrue(repository.update(avatar));
    }

    @Test
    @Order(2)
    void shouldDelete() {
        assertTrue(repository.deleteById(2));
    }

    private Avatar makeAvatar() {
        Avatar avatar = new Avatar();
        avatar.setAvatarImageUrl("https://cdn.pixabay.com/photo/2017/05/25/21/26/bird-feeder.jpg");
        avatar.setAvatarDescription("Bird Feeder");
        avatar.setAvatarId(3);
        return avatar;
    }
}