package indigodev.com.co.springinventoryapi.service;


import indigodev.com.co.springinventoryapi.dto.request.movement.CreateMovementRequest;
import indigodev.com.co.springinventoryapi.dto.response.movement.MovementResponse;

public interface MovementService {
    MovementResponse createMovement(CreateMovementRequest request);
    MovementResponse findById(Long id);
    void delete(Long id);
}
