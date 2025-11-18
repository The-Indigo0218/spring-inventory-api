package indigodev.com.co.springinventoryapi.service.impl;

import indigodev.com.co.springinventoryapi.domain.Product;
import indigodev.com.co.springinventoryapi.dto.request.product.CreateProductRequest;
import indigodev.com.co.springinventoryapi.dto.response.product.ProductResponse;
import indigodev.com.co.springinventoryapi.exception.ResourceNotFoundException;
import indigodev.com.co.springinventoryapi.repository.ProductRepository;
import indigodev.com.co.springinventoryapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .build();
        product = productRepository.save(product);
        return mapToResponse(product);
    }

    @Override
    public ProductResponse findById(Long id) {
       Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
       return mapToResponse(product);
    }

    @Override
    public ProductResponse findByName(String name) {
        Product product = productRepository.findProductByNameIs((name)).orElseThrow(() -> new ResourceNotFoundException("Product not found with name " + name));
        return mapToResponse(product);
    }

    @Override
    public void delete(Long id) {

    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getStock(), product.getCreatedAt());
    }
}
