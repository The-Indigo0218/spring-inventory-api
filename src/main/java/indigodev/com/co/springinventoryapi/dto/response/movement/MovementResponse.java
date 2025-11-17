package indigodev.com.co.springinventoryapi.dto.response.movement;

import indigodev.com.co.springinventoryapi.dto.response.product.ProductResponse;

import java.time.LocalDateTime;

public record MovementResponse(ProductResponse product, String type, Double quantity, LocalDateTime createdAt) {
}
