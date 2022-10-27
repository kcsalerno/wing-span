package learn.wingspan.controllers;

import learn.wingspan.domain.BirdService;
import learn.wingspan.domain.Result;
import learn.wingspan.domain.ResultType;
import learn.wingspan.models.Bird;
import learn.wingspan.models.Sighting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/bird")
public class BirdController {
    private final BirdService service;

    public BirdController(BirdService service) {
        this.service = service;
    }

    @GetMapping
    public List<Bird> findAll() {
        return service.findAll();
    }

    @GetMapping("/{birdId}")
    public ResponseEntity<Bird> findById(@PathVariable int birdId) {
        Bird bird = service.findById(birdId);

        if (bird == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bird);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Bird bird) {
        Result<Bird> result = service.add(bird);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{birdId}")
    public ResponseEntity<Object> update(@PathVariable int birdId, @RequestBody Bird bird) {
        if (birdId != bird.getBirdId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Bird> result = service.update(bird);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{birdId}")
    public ResponseEntity<Void> deleteById(@PathVariable int birdId) {
        Result<Bird> result = service.deleteById(birdId);
        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
