package udistrital.avanzada.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udistrital.avanzada.usuarios.modelo.UsuarioDTO;

/**
 * Repositorio de acceso a datos para la entidad UsuarioDTO.
 * <p>
 * Permite ejecutar operaciones CRUD y consultas personalizadas.
 * </p>
 *
 * @author Diego
 * @version 1.0
 */
public interface UsuarioRepository extends JpaRepository<UsuarioDTO, Long> {

    /**
     * Busca un usuario por su nickname.
     *
     * @param nickname nickname único del usuario
     * @return UsuarioDTO encontrado o null si no existe
     */
    UsuarioDTO findByNickname(String nickname);
}
