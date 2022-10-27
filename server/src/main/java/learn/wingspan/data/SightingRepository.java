package learn.wingspan.data;

import learn.wingspan.models.Sighting;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SightingRepository {
    List<Sighting> findAll();

    Sighting findById(int sightingId);

    Sighting create(Sighting sighting);

    boolean update(Sighting sighting);
    // boolean deleteByKey(int sightingId, int appUserId, int birdId) throws DataAccessException;

    @Transactional
    boolean deleteById(int sightingId);
}