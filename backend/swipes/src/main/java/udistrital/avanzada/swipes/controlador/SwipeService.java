package udistrital.avanzada.swipes.controlador;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import udistrital.avanzada.swipes.modelo.MatchRequest;
import udistrital.avanzada.swipes.modelo.SwipeDTO;
import udistrital.avanzada.swipes.modelo.SwipeRequest;
import udistrital.avanzada.swipes.modelo.SwipeResponse;
import udistrital.avanzada.swipes.resposotory.SwipeRepository;

/**
 * MatchService gestiona el acceso a los modelos de Foto
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Service
public class SwipeService {

    //inyeccion automatica repositorio
    @Autowired
    private SwipeRepository repositorio;
    //inyeccion automatica restTemplate para comunicacion con otros microservicios
    @Autowired
    private RestTemplate restTemplate;
    //inyeccion automatica ModelMapper para pasar de DTOs a Responses
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Metodo para crear un swipe
     *
     * @param swipe
     * @return
     */
    public SwipeResponse crearMatch(SwipeRequest swipe) {
        //ya hay un swipe creado
        if (repositorio.findByUserIdAndTargetUserId(swipe.getUserId(), swipe.getTargetUserId()).isPresent()) {
            return null;
        }
        SwipeDTO aGuardar = new SwipeDTO();
        aGuardar.setUserId(swipe.getUserId());
        aGuardar.setTargetUserId(swipe.getTargetUserId());
        aGuardar.setSwipeType(swipe.getSwipeType());

        SwipeDTO guardado = repositorio.save(aGuardar);
        SwipeResponse res = null;
        if (guardado != null) {
            if (!repositorio.findPotentialMatches(guardado.getUserId(), guardado.getTargetUserId()).isEmpty()) {
                MatchRequest request = new MatchRequest(guardado.getUserId(), guardado.getTargetUserId(), true);
                String url = "http://localhost:8093/matches/match";
                Boolean response = restTemplate.postForObject(url, request, Boolean.class);
            }
            res = modelMapper.map(guardado, SwipeResponse.class);
        }
        return res;
    }

    /**
     * Metodo para obtener todos los swipes del usuario
     *
     * @param idUsuario
     * @return
     */
    public List<SwipeResponse> getAllSwipesUsuario(Long idUsuario) {
        List<SwipeDTO> matches = repositorio.findAllByUserId(idUsuario);
        return matches.stream().map(entidad -> modelMapper.map(entidad, SwipeResponse.class)).toList();
    }
}
