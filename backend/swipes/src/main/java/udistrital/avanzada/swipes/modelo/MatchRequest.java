package udistrital.avanzada.swipes.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MatchRequest representa el objeto para enviar peticion
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchRequest {
    private long usuario1Id;
    private long usuario2Id;    
    private boolean activo;
}
