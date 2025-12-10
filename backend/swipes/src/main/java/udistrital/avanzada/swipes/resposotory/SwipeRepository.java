package udistrital.avanzada.swipes.resposotory;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import udistrital.avanzada.swipes.modelo.SwipeDTO;

/**
 * SwipeRepository se encarga de la comunicacion con el repositorio
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Repository
public interface SwipeRepository extends JpaRepository<SwipeDTO, Long> {
    //Obtener todos los swipes de un usuario
    List<SwipeDTO> findAllByUserId(Long userId);
    
    /**
     * Busca un swipe de un usuario hacia otro
     */
    Optional<SwipeDTO> findByUserIdAndTargetUserId(Long userId, Long targetUserId);

    /**
     * Verifica si hay un like mutuo entre dos usuarios
     * @param userId
     * @param targetUserId
     * @return 
     */
    @Query("SELECT s FROM SwipeDTO s WHERE " +
           "((s.userId = :userId AND s.targetUserId = :targetUserId) OR " +
           " (s.userId = :targetUserId AND s.targetUserId = :userId)) AND " +
           "(s.swipeType = 'LIKE' OR s.swipeType = 'SUPERLIKE')")
    List<SwipeDTO> findPotentialMatches(@Param("userId") Long userId,
                                        @Param("targetUserId") Long targetUserId);
}
