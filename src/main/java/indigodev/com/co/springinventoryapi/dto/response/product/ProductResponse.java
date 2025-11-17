package indigodev.com.co.springinventoryapi.dto.response.product;

import java.time.LocalDateTime;

public record ProductResponse(Long id, String name, Double stock, LocalDateTime createdAt) {
}
