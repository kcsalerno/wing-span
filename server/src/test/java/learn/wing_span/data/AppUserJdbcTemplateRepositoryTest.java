package learn.wing_span.data;

import learn.wing_span.models.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AppUserJdbcTemplateRepositoryTest {

    @Autowired
    AppUserJdbcTemplateRepository repository;

    @Test
    void shouldFindAdmin() {
        AppUser actual = repository.findByEmail("admin@admin.com");
        assertNotNull(actual);
    }

    @Test
    void shouldFindUser() {
        AppUser actual = repository.findByEmail("user@user.com");
        assertNotNull(actual);
    }

    @Test
    void shouldNotFindBlankEmail() {
        AppUser actual = repository.findByEmail("");
        assertNull(actual);
    }
}