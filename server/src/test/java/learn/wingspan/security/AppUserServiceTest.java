package learn.wingspan.security;

import learn.wingspan.data.AppUserJdbcTemplateRepository;
import learn.wingspan.data.AppUserRepository;
import learn.wingspan.domain.Result;
import learn.wingspan.models.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AppUserServiceTest {

    @MockBean
    AppUserJdbcTemplateRepository repository;

    @Autowired
    AppUserService service;

    @Test
    void shouldLoadUserByUsername() {
        when(repository.findByUsername("admin1")).thenReturn(
                new AppUser(1, "admin1", "P@ssw0rd!", true,
                        makeRole()
        ));

        AppUser appUser = (AppUser) service.loadUserByUsername("admin1");

        assertNotNull(appUser);
        assertEquals("admin1", appUser.getUsername());
    }

    @Test
    void shouldCreateNewUser() {
        String username = "testUser";
        String password = "P@ssw0rd!";

        Result<AppUser> result = service.create(username, password);

        assertTrue(result.isSuccess());

    }

    @Test
    void shouldNotCreateNewUserWithNullOrBlankUsername() {
        String password = "P@ssw0rd!";
        Result<AppUser> result = service.create(null, password);


    }

    private static List<String> makeRole () {
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        return roles;
    }
}