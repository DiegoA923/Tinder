package udistrital.avanzada.matches.modelo;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MatchResponse representa el objeto que se envia comon respuesta a los clientes
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchResponse {
    private Long id;
    private long usuario1Id;
    private long usuario2Id;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
}

