package indigodev.com.co.springinventoryapi.service;

import indigodev.com.co.springinventoryapi.domain.Product;

public interface ProductService {
    Product save(String name);
    Product fiendById(int id);
    Product fiendByName(String name);
    void delete(int id);
}
