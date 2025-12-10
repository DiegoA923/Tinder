package udistrital.avanzada.usuarios.modelo;

import lombok.Data;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Objeto de respuesta para enviar información segura del usuario al FrontEnd.
 * <p>
 * Esta clase no contiene anotaciones JPA. Es usada únicamente para retornar
 * datos visibles por el cliente evitando exponer información sensible.
 * </p>
 *
 * @author Diego
 * @version 1.0
 * @since 2025-12-09
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioResponse {

    private Long id;
    private String nickname;
    private String nombre;
    private Integer edad;
    private String ciudad;
    private String correo;
    private LocalDate fechaRegistro;
    private String descripcion;
    private String genero;
    private String fotoPerfil;
}
