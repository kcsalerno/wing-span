package learn.wingspan.data;

import learn.wingspan.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppUserJdbcTemplateRepositoryTest {
    private final int USER_COUNT = 2;

    @Autowired
    AppUserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindByAdminByUsername() {
        AppUser actual = repository.findByUsername("admin1");
        assertNotNull(actual);
    }

    @Test
    void shouldFindByUserByUsername() {
        AppUser actual = repository.findByUsername("user1");
        assertNotNull(actual);
    }

    @Test
    void shouldNotFindUserWithBlankOrNullUsername() {
        AppUser actual = repository.findByUsername("");
        assertNull(actual);

        actual = repository.findByUsername(null);
        assertNull(actual);
    }

    @Test
    void shouldCreateNewUser() {
        AppUser actual = repository.create(makeAppUser());

        assertNotNull(actual);
        assertEquals(USER_COUNT + 1, actual.getAppUserId());
    }


    @Test
    void shouldUpdateCurrentUser() {
        AppUser actual = repository.findByUsername("user1");
        assertNotNull(actual);

        actual.setEnabled(false);
        assertTrue(repository.update(actual));

        assertEquals("user@user.com", actual.getEmail());
        assertFalse(actual.isEnabled());

        actual.setEmail("test@testing.com");
        assertTrue(repository.update(actual));

        assertEquals("user1", actual.getUsername());
        assertEquals("test@testing.com", actual.getEmail());
    }

    private AppUser makeAppUser() {
        List<String> roles = new ArrayList<>();
        roles.add("USER");

        return new AppUser(0, "test", "test", true,
                "test@test.com", roles);
    }
}