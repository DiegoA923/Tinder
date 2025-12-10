package udistrital.avanzada.swipes.controlador;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import udistrital.avanzada.swipes.modelo.SwipeRequest;
import udistrital.avanzada.swipes.modelo.SwipeResponse;


/**
 * MatchController se encarga del manejo y comunicacion de la API REST
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@RestController

public class SwipeController {
    //Inyeccion automatica
    @Autowired
    private SwipeService servicios;
    
    /**
     * Metodo para obtener los match activos de un usuario
     * @param idUsuario
     * @return 
     */
    @RequestMapping(value = "/swipes/{idUsuario}", method = RequestMethod.GET)
    @CrossOrigin(origins = {"http://localhost:8383","http://localhost:8090","http://localhost:8095"})
    public ResponseEntity<List<SwipeResponse>> buscarMatchesActivos(@PathVariable Long idUsuario) {
        List<SwipeResponse> swipes = servicios.getAllSwipesUsuario(idUsuario);
        return ResponseEntity.ok(swipes);
    }        
    
    /**
     * Metodo para crear un swipe
     * 
     * @param swipe
     * @return 
     */
    @RequestMapping(value = "/match", method = RequestMethod.POST)
    //Solo microservicio de swipe crea los match
    @CrossOrigin(origins = {"http://localhost:8095"})
    public ResponseEntity<SwipeResponse> crearMatch(@Valid @RequestBody SwipeRequest swipe) {
        SwipeResponse res = servicios.crearMatch(swipe);
        if (res != null) {
            return ResponseEntity.status(201).body(res);
        }
        return ResponseEntity.status(500).body(res);
    }
}
