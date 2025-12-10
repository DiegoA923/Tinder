package udistrital.avanzada.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Respuesta estándar para operaciones de envío de correo.
 * <p>
 * Utilizada para enviar mensajes controlados hacia el FrontEnd o hacia
 * otros microservicios que consuman este servicio.
 * </p>
 *
 * @author Diego
 * @version 1.0
 * @date 2025-12-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailResponse {
    
    private String mensaje;   // Mensaje informativo del resultado
    private boolean enviado;  // Indica si el correo se envió correctamente
}
