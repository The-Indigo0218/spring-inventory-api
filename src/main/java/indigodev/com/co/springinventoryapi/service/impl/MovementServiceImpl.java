package indigodev.com.co.springinventoryapi.service.impl;

import indigodev.com.co.springinventoryapi.domain.Movement;
import indigodev.com.co.springinventoryapi.domain.MovementType;
import indigodev.com.co.springinventoryapi.domain.Product;
import indigodev.com.co.springinventoryapi.dto.request.movement.CreateMovementRequest;
import indigodev.com.co.springinventoryapi.dto.response.movement.MovementResponse;
import indigodev.com.co.springinventoryapi.dto.response.product.ProductResponse;
import indigodev.com.co.springinventoryapi.exception.InsufficientStockException;
import indigodev.com.co.springinventoryapi.exception.InvalidValueException;
import indigodev.com.co.springinventoryapi.exception.ResourceNotFoundException;
import indigodev.com.co.springinventoryapi.repository.MovementRepository;
import indigodev.com.co.springinventoryapi.repository.ProductRepository;
import indigodev.com.co.springinventoryapi.service.MovementService;
import indigodev.com.co.springinventoryapi.util.InventoryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final ProductRepository productRepository;
    private final InventoryMapper inventoryMapper;

    @Transactional
    @Override
    public MovementResponse createMovement(CreateMovementRequest request) {
        Product product = productRepository.findById(request.productId()).orElseThrow(() -> new ResourceNotFoundException("No found product with id: " + request.productId()));
       MovementType type;
       try {
       type = MovementType.valueOf(request.type());
       }catch (IllegalArgumentException e){
            throw new InvalidValueException(request.type() + " is a invalid movement type");
       }
        Movement movement =  Movement.builder()
                .quantity(request.quantity())
                .type(type)
                .build();

       if (MovementType.ENTRY.equals(type)) product.setStock( product.getStock() + request.quantity() );
       else{
           if (product.getStock() < request.quantity())
               throw new InsufficientStockException(product.getId(), request.quantity(),  product.getStock());
           product.setStock( product.getStock() - request.quantity());
       }

       product.addMovement(movement);
       movement =  movementRepository.save(movement);
       return inventoryMapper.mapToResponseMovement(movement);
    }

    @Override
    public MovementResponse findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }


}
