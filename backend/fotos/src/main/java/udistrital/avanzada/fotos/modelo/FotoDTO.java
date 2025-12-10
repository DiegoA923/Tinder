package udistrital.avanzada.fotos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * FotoDTO representa la foto
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Entity
@Table(name = "fotos")
@Data
public class FotoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Url es obligatorio")
    private String url;
    @NotNull
    private Long usuarioId;
    private LocalDateTime fechaCreacion;
       
    public FotoDTO() {
    }

    public FotoDTO(String url, Long usuarioId) {
        this.url = url;
        this.usuarioId = usuarioId;
    }
    
    /**
     * Se ejecuta antes de insertar el registro en base de datos. Establece
     * automáticamente la fecha de registro.
     */
    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
