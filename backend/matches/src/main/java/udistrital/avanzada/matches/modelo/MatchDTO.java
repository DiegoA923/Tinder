package udistrital.avanzada.matches.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MatcheDTO representa un match dentro de al aplicacion
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive
    private long usuario1Id;
    @NotNull
    @Positive
    private long usuario2Id;
    @NotNull
    private boolean activo;
    @NotNull
    private LocalDateTime fechaCreacion;
    @NotNull
    private LocalDateTime fechaModificacion;
}
