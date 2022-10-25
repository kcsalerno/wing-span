package learn.wing_span.data;

import learn.wing_span.models.Sighting;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SightingRepository {
    List<Sighting> findAll();
    Sighting findById(int sightingId);
    Sighting create(Sighting sighting);
    boolean update(Sighting sighting);
    boolean deleteByKey(int sightingId, int appUserId, int birdId) throws DataAccessException;
}
