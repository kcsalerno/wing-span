package learn.wingspan.controllers;

import learn.wingspan.domain.Result;
import learn.wingspan.domain.ResultType;
import learn.wingspan.domain.SightingService;
import learn.wingspan.models.Sighting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
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
        return makeResponseEntity(result, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{sightingId}")
    public ResponseEntity<Object> update(@PathVariable int sightingId, @RequestBody Sighting sighting) {
        if (sightingId != sighting.getSightingId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Sighting> result = service.update(sighting);
        if (!result.isSuccess()) {
            if (result.getType() == ResultType.NOT_FOUND) {
                return makeResponseEntity(result, HttpStatus.NOT_FOUND);
            } else {
                return makeResponseEntity(result, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{sightingId}")
    public ResponseEntity<Void> deleteById(@PathVariable int sightingId) {
        Result result = service.deleteById(sightingId);
        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private <T> ResponseEntity<Object> makeResponseEntity(Result<T> result, HttpStatus status) {
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        } else if (result.getType() ==ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result.getPayload(), status);
    }
}
