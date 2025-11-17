package indigodev.com.co.springinventoryapi.service;

import indigodev.com.co.springinventoryapi.domain.Product;
import indigodev.com.co.springinventoryapi.dto.product.CreateProductRequest;

public interface ProductService {
    Product save(CreateProductRequest createProductRequest);
    Product fiendById(int id);
    Product fiendByName(String name);
    void delete(int id);
}
