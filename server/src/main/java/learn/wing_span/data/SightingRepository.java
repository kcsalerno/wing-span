package learn.wing_span.data;

import learn.wing_span.models.Sighting;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SightingRepository {
    List<Sighting> findAll() throws DataAccessException;
    Sighting findById(int sightingId) throws DataAccessException;
    Sighting create(Sighting sighting) throws DataAccessException;
    boolean update(Sighting sighting) throws  DataAccessException;
    boolean deleteById(int id) throws DataAccessException;
}
