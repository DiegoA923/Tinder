package udistrital.avanzada.swipes.modelo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SwipeRequest representa el objeto que envia el cliente con su peticion
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwipeRequest {

    @NotNull(message = "El ID del usuario que hace swipe es obligatorio")
    private Long userId;

    @NotNull(message = "El ID del usuario objetivo es obligatorio")
    private Long targetUserId;

    @NotNull(message = "El tipo de swipe es obligatorio")
    private SwipeType swipeType;
}
