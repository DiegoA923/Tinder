package udistrital.avanzada.matches.controlador;

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
import udistrital.avanzada.matches.modelo.MatchRequest;
import udistrital.avanzada.matches.modelo.MatchResponse;


/**
 * MatchController se encarga del manejo y comunicacion de la API REST
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@RestController

public class MatchController {
    //Inyeccion automatica
    @Autowired
    private MatchService servicios;
    
    /**
     * Metodo para obtener los match activos de un usuario
     * @param idUsuario
     * @return 
     */
    @RequestMapping(value = "/matches/{idUsuario}", method = RequestMethod.GET)
    @CrossOrigin(origins = {"http://localhost:8383","http://localhost:8090","http://localhost:8095"})
    public ResponseEntity<List<MatchResponse>> buscarMatchesActivos(@PathVariable Long idUsuario) {
        List<MatchResponse> matches = servicios.getAllMatchesActivoUsuario(idUsuario);
        return ResponseEntity.ok(matches);
    }        
    
    /**
     * Metodo para modficar un match
     * 
     * @param id
     * @param match
     * @return 
     */     
    @RequestMapping(value = "/match/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = {"http://localhost:8383","http://localhost:8090","http://localhost:8095"})
    public ResponseEntity<MatchResponse> modificarMatch(@PathVariable Long id, @Valid @RequestBody MatchRequest match) {
        MatchResponse res = servicios.modificarMatch(id, match);
        if (res != null) {
            return ResponseEntity.status(201).body(res);
        }
        return ResponseEntity.status(500).body(res);
    }
    
    /**
     * Metodo para crear un match
     * 
     * @param match
     * @return 
     */
    @RequestMapping(value = "/match", method = RequestMethod.POST)
    //Solo microservicio de swipe crea los match
    @CrossOrigin(origins = {"http://localhost:8095"})
    public ResponseEntity<MatchResponse> crearMatch(@Valid @RequestBody MatchRequest match) {
        MatchResponse res = servicios.crearMatch(match);
        if (res != null) {
            return ResponseEntity.status(201).body(res);
        }
        return ResponseEntity.status(500).body(res);
    }
}
