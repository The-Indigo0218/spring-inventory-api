package indigodev.com.co.springinventoryapi.repository;

import indigodev.com.co.springinventoryapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
