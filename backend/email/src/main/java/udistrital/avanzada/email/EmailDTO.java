package udistrital.avanzada.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO Email.
 * <p>
 * Representa la información necesaria para enviar un correo electrónico
 * dentro del sistema. Este objeto será recibido por el controlador cuando
 * otro microservicio solicite el envío de un email.
 * </p>
 *
 * @author Diego
 * @version 1.0
 * @since 2025-12-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

    @Email(message = "Debe ser un correo válido")
    @NotBlank(message = "El destinatario es obligatorio")
    private String destinatario;

    @NotBlank(message = "El asunto no puede estar vacío")
    private String asunto;

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String mensaje;
}
