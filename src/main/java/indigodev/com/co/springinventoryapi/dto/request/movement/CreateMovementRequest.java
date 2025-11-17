package indigodev.com.co.springinventoryapi.dto.request.movement;

import java.time.LocalDateTime;

public record CreateMovementRequest(Long productId, String type, Double quantity) {
}
