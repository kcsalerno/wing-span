package learn.wing_span.data;

import learn.wing_span.models.Bird;
import learn.wing_span.models.Sighting;

import java.util.List;

public interface BirdRepository {
    List<Bird> findAll();
    Bird findById(int birdId);
    Bird add(Bird bird);
    boolean update(Bird bird);
    boolean deleteById(int birdId);
}
