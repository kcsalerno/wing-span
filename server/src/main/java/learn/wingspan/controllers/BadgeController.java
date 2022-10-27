package learn.wingspan.controllers;

import learn.wingspan.domain.BadgeService;
import learn.wingspan.domain.Result;
import learn.wingspan.domain.ResultType;
import learn.wingspan.models.Badge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/badge")
public class BadgeController {

    private final BadgeService service;

    public BadgeController(BadgeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Badge> findAll() {
        return service.findAll();
    }

    @GetMapping("/{badgeId}")
    public ResponseEntity<Badge> findById(@PathVariable int badgeId) {
        Badge badge = service.findById(badgeId);
        if (badge == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(badge);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Badge badge) {
        Result<Badge> result = service.add(badge);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{badgeId}")
    public ResponseEntity<?> update(@PathVariable int badgeId, @RequestBody Badge badge) {
        if (badgeId != badge.getBadgeId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Badge> result = service.update(badge);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{badgeId}")
    public ResponseEntity<Void> deleteById(@PathVariable int badgeId) {
        Result<Badge> result = service.deleteById(badgeId);
        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
