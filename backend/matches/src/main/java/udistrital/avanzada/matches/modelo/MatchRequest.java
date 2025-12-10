package udistrital.avanzada.matches.modelo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MatchRequest representa el objeto que envia el cliente con su peticion
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
    @NotNull
    @Positive
    private long usuario1Id;
    @NotNull
    @Positive
    private long usuario2Id;
    @NotNull
    private boolean activo;    
}