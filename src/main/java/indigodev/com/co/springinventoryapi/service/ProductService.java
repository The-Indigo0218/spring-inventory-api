package indigodev.com.co.springinventoryapi.service;

import indigodev.com.co.springinventoryapi.dto.request.product.CreateProductRequest;
import indigodev.com.co.springinventoryapi.dto.response.product.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse findById(Long id);
    ProductResponse findByName(String name);
    void delete(Long id);
    List<ProductResponse> findAll();
}
