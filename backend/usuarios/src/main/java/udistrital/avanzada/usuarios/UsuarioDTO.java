package udistrital.avanzada.usuarios;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

/**
 * DTO Usuario.
 * <p>
 * Representa un usuario dentro del sistema Tinder, almacenado en la base de
 * datos mediante JPA. Esta clase contiene las validaciones necesarias para
 * garantizar la correcta integridad de los datos durante el registro.
 * </p>
 *
 * @author Diego
 * @version 1.0
 * @since 2025-12-09
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class UsuarioDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; // Identificador único del usuario en la base de datos

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nickname es obligatorio")
    private String nickname; // Nickname único del usuario
    
    @NotBlank(message = "El nombre no puede ser vacío")
    private String nombre; // Nombre del usuario
    
    @Min(value = 18, message = "Debes tener al menos 18 años")
    private Integer edad;
    
    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @Email(message = "Debe ser un correo válido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
    private LocalDate fechaRegistro;
    private String descripcion;
    private String genero;
    private String fotoPerfil;

    /**
     * Se ejecuta antes de insertar el registro en base de datos. Establece
     * automáticamente la fecha de registro.
     */
    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDate.now();
    }
}
