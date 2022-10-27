package learn.wingspan.controllers;

public class AvatarController {
=======
import learn.wingspan.domain.AvatarService;
import learn.wingspan.domain.Result;
import learn.wingspan.domain.ResultType;
import learn.wingspan.models.Avatar;
import learn.wingspan.models.Bird;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/avatar")
public class AvatarController {
    private final AvatarService service;

    public AvatarController(AvatarService service) {
        this.service = service;
    }

    @GetMapping
    public List<Avatar> findAll() {
        return service.findAll();
    }

    @GetMapping("/{avatarId}")
    public ResponseEntity<Avatar> findById(@PathVariable int avatarId) {
        Avatar avatar = service.findById(avatarId);

        if (avatar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(avatar);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Avatar avatar) {
        Result<Avatar> result = service.add(avatar);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{avatarId}")
    public ResponseEntity<Object> update(@PathVariable int avatarId, @RequestBody Avatar avatar) {
        if (avatarId != avatar.getAvatarId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Avatar> result = service.update(avatar);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{avatarId}")
    public ResponseEntity<Void> deleteById(@PathVariable int avatarId) {
        Result<Avatar> result = service.deleteById(avatarId);
        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
