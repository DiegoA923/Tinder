package udistrital.avanzada.fotos.modelo;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FotoResponse representa el objeto que se envia comon respuesta a los clientes
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FotoResponse {
    private Long id;   
    private String url;
    private Long usuarioId;
    private LocalDateTime fechaCreacion;
}
