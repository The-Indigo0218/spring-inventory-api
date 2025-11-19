package indigodev.com.co.springinventoryapi.service.impl;

import indigodev.com.co.springinventoryapi.dto.request.movement.CreateMovementRequest;
import indigodev.com.co.springinventoryapi.dto.response.movement.MovementResponse;
import indigodev.com.co.springinventoryapi.service.MovementService;
import org.springframework.stereotype.Service;

@Service
public class MovementServiceImpl implements MovementService {
    @Override
    public MovementResponse createMovement(CreateMovementRequest request) {
        return null;
    }

    @Override
    public MovementResponse findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
