package udistrital.avanzada.swipes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SwipeDTO representa un match dentro de al aplicacion
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Entity
@Table(name = "swipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwipeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "El ID del usuario que hace swipe es obligatorio")
    private Long userId;

    @NotNull(message = "El ID del usuario objetivo es obligatorio")
        private Long targetUserId;

    @NotNull(message = "El tipo de swipe es obligatorio")
    private SwipeType swipeType;

    private LocalDateTime fechaCreacion;

    /**
     * Se ejecuta antes de insertar el registro en base de datos. Establece
     * automáticamente la fecha de registro.
     */
    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
