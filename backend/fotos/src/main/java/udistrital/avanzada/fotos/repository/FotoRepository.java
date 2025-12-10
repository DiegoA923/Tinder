package udistrital.avanzada.fotos.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udistrital.avanzada.fotos.modelo.FotoDTO;

/**
 * FotoRepository se encarga de la comunicacion con el repositorio
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Repository
public interface FotoRepository extends JpaRepository<FotoDTO, Long>{
    //Metodo para obtener todas las fotos de un usuario
    List<FotoDTO> findAllByUsuarioId(Long usuarioId);
    //Metodo para borrar todas las fotos de un usuario
    @Transactional
    long deleteByUsuarioId(Long usuarioId);
}
