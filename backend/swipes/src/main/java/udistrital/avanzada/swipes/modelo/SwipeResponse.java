package udistrital.avanzada.swipes.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * SwipeResponse representa el objeto que se envia comon respuesta a los clientes
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwipeResponse {

    private Long id;
    private Long userId;
    private Long targetUserId;
    private SwipeType swipeType;
    private LocalDateTime fechaCreacion;
}
