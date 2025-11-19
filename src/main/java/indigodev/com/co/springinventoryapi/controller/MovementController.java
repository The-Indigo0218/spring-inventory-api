package indigodev.com.co.springinventoryapi.controller;

import indigodev.com.co.springinventoryapi.dto.request.movement.CreateMovementRequest;
import indigodev.com.co.springinventoryapi.dto.response.movement.MovementResponse;
import indigodev.com.co.springinventoryapi.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movements")
public class MovementController {

    final MovementService movementService;

    @GetMapping("/{id}")
    public ResponseEntity<MovementResponse> findById(@PathVariable Long id) {
        MovementResponse movement = movementService.findById(id);
        return ResponseEntity.ok(movement);
    }

    @PostMapping
    public ResponseEntity<MovementResponse> save(@RequestBody CreateMovementRequest request) {
        MovementResponse response = movementService.createMovement(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
