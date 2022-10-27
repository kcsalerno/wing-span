package learn.wingspan.controllers;

public class AvatarController {
=======
import learn.wingspan.domain.AvatarService;
import learn.wingspan.models.Avatar;
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
}
