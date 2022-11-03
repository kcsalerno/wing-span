package learn.wingspan.controllers;

import learn.wingspan.domain.Result;
import learn.wingspan.domain.ResultType;
import learn.wingspan.domain.TraitService;
import learn.wingspan.models.Trait;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("api/trait")
public class TraitController {

    private final TraitService service;

    public TraitController(TraitService service) {
        this.service = service;
    }

    @GetMapping
    public List<Trait> findAll() {
        return service.findAll();
    }

    @GetMapping("/{traitId}")
    public ResponseEntity<Trait> findById(@PathVariable int traitId) {
        Trait trait = service.findById(traitId);
        if (trait == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(trait);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Trait trait) {
        Result<Trait> result = service.add(trait);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{traitId}")
    public ResponseEntity<?> update(@PathVariable int traitId, @RequestBody Trait trait) {
        if (traitId != trait.getTraitId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Trait> result = service.update(trait);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{traitId}")
    public ResponseEntity<Void> deleteById(@PathVariable int traitId) {
        Result<Trait> result = service.deleteById(traitId);
        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
