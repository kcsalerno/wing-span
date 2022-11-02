package learn.wingspan.controllers;

import learn.wingspan.domain.Result;
import learn.wingspan.domain.ResultType;
import learn.wingspan.domain.SightingService;
import learn.wingspan.models.Sighting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/sighting")
public class SightingController {
    private final SightingService service;

    public SightingController(SightingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Sighting> findAll() {
        return service.findAll();
    }

    @GetMapping("/{sightingId}")
    public ResponseEntity<Sighting> findById(@PathVariable int sightingId) {
        Sighting sighting = service.findById(sightingId);
        if (sighting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sighting);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Sighting sighting) {
        Result<Sighting> result = service.create(sighting);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{sightingId}")
    public ResponseEntity<Object> update(@PathVariable int sightingId, @RequestBody Sighting sighting) {
        if (sightingId != sighting.getSightingId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Sighting> result = service.update(sighting);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{sightingId}")
    public ResponseEntity<Void> deleteById(@PathVariable int sightingId) {
        Result<Sighting> result = service.deleteById(sightingId);
        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
