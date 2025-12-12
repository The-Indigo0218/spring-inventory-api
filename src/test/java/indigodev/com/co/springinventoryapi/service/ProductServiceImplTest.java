package indigodev.com.co.springinventoryapi.service;

import indigodev.com.co.springinventoryapi.domain.Product;
import indigodev.com.co.springinventoryapi.dto.request.product.CreateProductRequest;
import indigodev.com.co.springinventoryapi.dto.response.product.ProductResponse;
import indigodev.com.co.springinventoryapi.repository.ProductRepository;
import indigodev.com.co.springinventoryapi.service.impl.ProductServiceImpl;
import indigodev.com.co.springinventoryapi.util.ResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ResponseMapper responseMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void createProduct_ShouldReturnResponse_WhenRequestIsValid() {
        CreateProductRequest request = new CreateProductRequest("Laptop");

        Product savedProduct = Product.builder()
                .id(1L)
                .name("Laptop")
                .stock(0.0)
                .createdAt(LocalDateTime.now())
                .build();


        ProductResponse expectedResponse = new ProductResponse(
                1L,
                "Laptop",
                0.0,
                savedProduct.getCreatedAt()
        );

        //Mocks training
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
        when(responseMapper.mapToResponseProduct(savedProduct)).thenReturn(expectedResponse);

        //WHEN: use real service
        ProductResponse result = productService.createProduct(request);

        // THEN: Verifications
        assertNotNull(result);
        assertEquals("Laptop", result.name());
        assertEquals(1L, result.id());

        //one call repository verify
        verify(productRepository, times(1)).save(any(Product.class));
    }
}