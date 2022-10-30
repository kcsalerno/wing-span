package learn.wingspan.data;

import learn.wingspan.models.AppUser;
import org.springframework.transaction.annotation.Transactional;

public interface AppUserRepository {

    @Transactional
    AppUser findByUsername(String username);

    @Transactional
    AppUser create(AppUser user);

//    @Transactional
//    void update(AppUser user);

    @Transactional
    boolean update(AppUser user);
}
