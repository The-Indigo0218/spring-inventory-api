package indigodev.com.co.springinventoryapi.util;

import indigodev.com.co.springinventoryapi.domain.Movement;
import indigodev.com.co.springinventoryapi.domain.Product;
import indigodev.com.co.springinventoryapi.dto.response.movement.MovementResponse;
import indigodev.com.co.springinventoryapi.dto.response.product.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public ProductResponse mapToResponseProduct(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getStock(), product.getCreatedAt());
    }

    public MovementResponse mapToResponseMovement(Movement movement) {
        ProductResponse productResponse = mapToResponseProduct(movement.getProduct());
        return new MovementResponse(productResponse, movement.getType().toString(), movement.getQuantity(), movement.getCreatedAt());
    }


}
