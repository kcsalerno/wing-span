//package learn.wingspan.security;
//
//import learn.wingspan.data.AppUserJdbcTemplateRepository;
//import learn.wingspan.data.AppUserRepository;
//import learn.wingspan.domain.Result;
//import learn.wingspan.models.AppUser;
//import learn.wingspan.models.Sighting;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class AppUserServiceTest {
//
//    @MockBean
//    AppUserJdbcTemplateRepository repository;
//
//    @Autowired
//    AppUserService service;
//
//    @Test
//    void shouldLoadUserByUsername() {
//        when(repository.findByUsername("admin1")).thenReturn(
//                new AppUser(1, "admin1", "P@ssw0rd!", true,
//                        makeRole()
//        ));
//
//        AppUser appUser = (AppUser) service.loadUserByUsername("admin1");
//
//        assertNotNull(appUser);
//        assertEquals("admin1", appUser.getUsername());
//    }
//
//    @Test
//    void shouldCreateNewUser() {
//        String username = "testUser";
//        String password = "P@ssw0rd!";
//
//        Result<AppUser> result = service.create(username, password);
//
//        assertTrue(result.isSuccess());
//
//    }
//
//    @Test
//    void shouldNotCreateNewUserWithNullOrBlankUsername() {
//        String password = "P@ssw0rd!";
//        Result<AppUser> result = service.create(null, password);
//
//        assertFalse(result.isSuccess());
//        assertEquals(1, result.getMessages().size());
//        assertEquals("username is required", result.getMessages().get(0));
//
//        result = service.create("", password);
//
//        assertFalse(result.isSuccess());
//        assertEquals(1, result.getMessages().size());
//        assertEquals("username is required", result.getMessages().get(0));
//    }
//
//    @Test
//    void shouldNotCreateNewUserWithNullOrBlankPassword() {
//        String username = "testUser";
//
//        Result<AppUser> result = service.create(username, null);
//
//        assertFalse(result.isSuccess());
//        assertEquals(1, result.getMessages().size());
//        assertEquals("password is required", result.getMessages().get(0));
//
//        result = service.create(username, "");
//
//        assertFalse(result.isSuccess());
//        assertEquals(1, result.getMessages().size());
//        assertEquals("password is required", result.getMessages().get(0));
//    }
//
//    @Test
//    void shouldNotCreateNewUserWithUsernameOver50Chars() {
//        String username = "thisTestUserUsernameIsJustWayWayWayTooLongToBeValid";
//        String password = "P@ssw0rd!";
//
//        Result<AppUser> result = service.create(username, password);
//
//        assertFalse(result.isSuccess());
//        assertEquals(1, result.getMessages().size());
//        assertTrue(result.getMessages().get(0).contains("must be less than 50"));
//    }
//
//    @Test
//    void shouldNotCreateNewUserWithInvalidPassword() {
//        String username = "testUser";
//        String password = "short";
//
//        Result<AppUser> result = service.create(username, password);
//
//        assertFalse(result.isSuccess());
//        assertEquals(1, result.getMessages().size());
//        assertTrue(result.getMessages().get(0).contains("password must be at least 8 characters"));
//
//        password = "ABCDEFGHI";
//
//        result = service.create(username, password);
//
//        assertFalse(result.isSuccess());
//        assertEquals(1, result.getMessages().size());
//        assertTrue(result.getMessages().get(0).contains("password must be at least 8 characters and contain a digit"));
//
//        password = "123456789";
//
//        result = service.create(username, password);
//
//        assertFalse(result.isSuccess());
//        assertEquals(1, result.getMessages().size());
//        assertTrue(result.getMessages().get(0).contains("password must be at least 8 characters and contain a digit, a letter"));
//
//        password = "ABCD12345";
//
//        result = service.create(username, password);
//
//        assertFalse(result.isSuccess());
//        assertEquals(1, result.getMessages().size());
//        assertEquals("password must be at least 8 characters and contain a digit," +
//                " a letter, and a non-digit/non-letter", result.getMessages().get(0));
//    }
//
//    private static List<String> makeRole () {
//        List<String> roles = new ArrayList<>();
//        roles.add("ADMIN");
//        return roles;
//    }
//}