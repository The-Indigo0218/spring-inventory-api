package indigodev.com.co.springinventoryapi.service;

import indigodev.com.co.springinventoryapi.domain.Movement;
import indigodev.com.co.springinventoryapi.domain.Product;
import indigodev.com.co.springinventoryapi.dto.request.movement.CreateMovementRequest;
import indigodev.com.co.springinventoryapi.dto.request.product.CreateProductRequest;
import indigodev.com.co.springinventoryapi.dto.response.movement.MovementResponse;
import indigodev.com.co.springinventoryapi.dto.response.product.ProductResponse;
import indigodev.com.co.springinventoryapi.exception.InsufficientStockException;
import indigodev.com.co.springinventoryapi.repository.MovementRepository;
import indigodev.com.co.springinventoryapi.repository.ProductRepository;
import indigodev.com.co.springinventoryapi.service.impl.MovementServiceImpl;
import indigodev.com.co.springinventoryapi.util.ResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovementServiceImplTest {
    @Mock
    private MovementRepository movementRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ResponseMapper responseMapper;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private MovementServiceImpl movementServiceImpl;

    @Test
    void createMovement_ShouldReturnResponse_WhenStockIsSufficient() {
        CreateProductRequest productRequest = new CreateProductRequest("Apple");
        CreateMovementRequest movementRequest = new CreateMovementRequest(1L, "ENTRY", 5.0);

        Product savedProduct = Product.builder()
                .id(1L)
                .name("Apple")
                .stock(10.0)
                .createdAt(LocalDateTime.now())
                .build();

        Product resultedProduct = Product.builder()
                .id(1L)
                .name("Apple")
                .stock(15.0)
                .createdAt(savedProduct.getCreatedAt())
                .build();

        ProductResponse expectedResponseProduct = new ProductResponse(
                1L,
                "Apple",
                15.0,
                savedProduct.getCreatedAt()
        );

        Movement savedMovement = Movement.builder()
                .id(1L)
                .quantity(5.0)
                .createdAt(LocalDateTime.now())
                .product(resultedProduct)
                .imageUrl("testWithoutImg")
                .build();

        MovementResponse expectedResponseMovement = new  MovementResponse(
                expectedResponseProduct,
                "ENTRY",
                5.0,
                savedMovement.getCreatedAt(),
                "testWithoutImg"
        );


        when(productRepository.findById(1L)).thenReturn(Optional.of(savedProduct));
        when(movementRepository.save(any(Movement.class))).thenReturn(savedMovement);
        when(responseMapper.mapToResponseMovement(savedMovement)).thenReturn(expectedResponseMovement);

        MovementResponse response = movementServiceImpl.createMovement(movementRequest);

        assertNotNull(response);
        assertEquals(expectedResponseMovement, response);
        assertEquals(15.0, savedProduct.getStock());
        assertEquals(5.0, response.quantity());

        verify(movementRepository, times(1)).save(any(Movement.class));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void createMovement_ShouldThrowException_WhenStockIsInsufficient() {
        CreateMovementRequest request = new CreateMovementRequest(1L, "EXIT", 10.0);

        Product savedProduct = Product.builder()
                .id(1L)
                .name("Apple")
                .stock(5.0)
                .build();


        when(productRepository.findById(1L)).thenReturn(Optional.of(savedProduct));

        assertThrows(InsufficientStockException.class, () -> {
            movementServiceImpl.createMovement(request);
        });

        verify(movementRepository, never()).save(any(Movement.class));
    }

}
