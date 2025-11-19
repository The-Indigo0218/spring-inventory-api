package indigodev.com.co.springinventoryapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
    public InsufficientStockException(Long productId, Double requestQuantity, Double stockQuantity) {
        super(String.format(
                "Insufficient stock for product with id: %d, solicitude: %.2f, available: %.2f", productId, requestQuantity,  stockQuantity
        ));
    }
}
