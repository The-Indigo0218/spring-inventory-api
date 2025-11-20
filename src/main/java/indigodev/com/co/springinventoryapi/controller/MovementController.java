package indigodev.com.co.springinventoryapi.controller;

import indigodev.com.co.springinventoryapi.dto.request.movement.CreateMovementRequest;
import indigodev.com.co.springinventoryapi.dto.response.movement.MovementResponse;
import indigodev.com.co.springinventoryapi.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movements")
public class MovementController {

    private final MovementService movementService;

    @GetMapping("/{id}")
    public ResponseEntity<MovementResponse> findById(@PathVariable Long id) {
        MovementResponse movement = movementService.findById(id);
        return ResponseEntity.ok(movement);
    }

    @PostMapping
    public ResponseEntity<MovementResponse> create(@RequestBody CreateMovementRequest request) {
        MovementResponse response = movementService.createMovement(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<List<MovementResponse>> findByProductName(@PathVariable String name) {
        return ResponseEntity.ok(movementService.findByProductName(name));
    }

}
