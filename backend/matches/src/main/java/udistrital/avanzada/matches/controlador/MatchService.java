package udistrital.avanzada.matches.controlador;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import udistrital.avanzada.matches.modelo.MatchDTO;
import udistrital.avanzada.matches.modelo.MatchRequest;
import udistrital.avanzada.matches.modelo.MatchResponse;
import udistrital.avanzada.matches.repository.MatchRepository;

/**
 * FotoService gestiona el acceso a los modelos de Foto
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Service
public class MatchService {

    //inyeccion automatica repositorio
    @Autowired
    private MatchRepository repositorio;
    //inyeccion automatica restTemplate para comunicacion con otros microservicios
    @Autowired
    private RestTemplate restTemplate;
    //inyeccion automatica ModelMapper para pasar de DTOs a Responses
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Metodo para crear un match
     *
     * @param match
     * @return
     */
    public MatchResponse crearMatch(MatchRequest match) {
        // Verificar si ya existe un match entre dos usuarios
        if (repositorio.existsMatchBetween(match.getUsuario1Id(), match.getUsuario2Id())) {
            return null;
        }
        System.out.println(repositorio.existsMatchBetween(match.getUsuario1Id(), match.getUsuario1Id()));
        MatchDTO aGuardar = new MatchDTO();
        aGuardar.setUsuario1Id(match.getUsuario1Id());
        aGuardar.setUsuario2Id(match.getUsuario2Id());
        aGuardar.setActivo(match.isActivo());
        aGuardar.setFechaCreacion(LocalDateTime.now());
        aGuardar.setFechaModificacion(LocalDateTime.now());
        MatchDTO guardado = repositorio.save(aGuardar);
        MatchResponse res = null;
        if (guardado != null) {
            res = modelMapper.map(guardado, MatchResponse.class);
        }
        return res;
    }

    /**
     * Metodo para obtener matches activos de usuario
     *
     * @param idUsuario
     * @return
     */
    public List<MatchResponse> getAllMatchesActivoUsuario(Long idUsuario) {
        List<MatchDTO> matches = repositorio.findMatchesActivosUsuario(idUsuario);
        return matches.stream().map(entidad -> modelMapper.map(entidad, MatchResponse.class)).toList();
    }

    /**
     * Metodo para modificar un match
     *
     * @param id
     * @param match
     * @return
     */
    public MatchResponse modificarMatch(Long id, MatchRequest match) {
        Optional<MatchDTO> actual = repositorio.findById(id);
        MatchResponse res = null;
        if (actual.isPresent()) {
            actual.get().setActivo(match.isActivo());
            actual.get().setFechaModificacion(LocalDateTime.now());
            res = modelMapper.map(repositorio.save(actual.get()), MatchResponse.class);
        }
        return res;
    }

}
