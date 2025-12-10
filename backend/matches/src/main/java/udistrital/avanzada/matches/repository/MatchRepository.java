package udistrital.avanzada.matches.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import udistrital.avanzada.matches.modelo.MatchDTO;

/**
 * FotoRepository se encarga de la comunicacion con el repositorio
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Repository
public interface MatchRepository extends JpaRepository<MatchDTO, Long> {

    @Query("SELECT m FROM MatchDTO m WHERE (m.usuario1Id = :id OR m.usuario2Id = :id) AND m.activo = true")
    List<MatchDTO> findMatchesActivosUsuario(@Param("id") Long usuarioId);

    // Verificar si ya existe un match entre dos usuarios
    @Query("""
       SELECT COUNT(m) > 0
       FROM MatchDTO m
       WHERE (m.usuario1Id = :user1 AND m.usuario2Id = :user2)
          OR (m.usuario1Id = :user2 AND m.usuario2Id = :user1)
       """)
    boolean existsMatchBetween(@Param("user1") Long usuario1Id,
            @Param("user2") Long usuario2Id);
}
