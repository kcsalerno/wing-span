package learn.wing_span.data;

import learn.wing_span.models.Avatar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
        Avatar actual= repository.findById(1);

        assertNotNull(actual);
        assertEquals(1, actual.getAvatarId());
        assertEquals("https://cdn.pixabay.com/photo/2017/05/25/21/26/bird-feeder-2344414__480.jpg", actual.getImageUrl());
    }

    @Test
    void shouldAdd() {
        Avatar avatar = makeAvatar();
        Avatar actual = repository.add(avatar);
        assertNotNull(actual);
        assertEquals(3, actual.getAvatarId());
    }

    @Test
    void shouldUpdate() {
        Avatar avatar = makeAvatar();
        avatar.setAvatarId(5);
        assertTrue(repository.update(avatar));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(2));
    }

    Avatar makeAvatar() {
        Avatar avatar = new Avatar();
        avatar.setImageUrl("https://cdn.pixabay.com/photo/2017/05/25/21/26/bird-feeder.jpg");
        avatar.setAvatarDescription("Bird Feeder");
        avatar.setAvatarId(3);
        return avatar;
    }

}