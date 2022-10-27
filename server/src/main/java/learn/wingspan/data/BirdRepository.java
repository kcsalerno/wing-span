package learn.wingspan.data;

import learn.wingspan.models.Bird;

import java.util.List;

public interface BirdRepository {
    List<Bird> findAll();

    Bird findById(int birdId);

    Bird add(Bird bird);

    boolean update(Bird bird);

    boolean deleteById(int birdId);
}
