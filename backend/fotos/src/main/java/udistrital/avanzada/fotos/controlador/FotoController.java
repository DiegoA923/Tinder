package udistrital.avanzada.fotos.controlador;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import udistrital.avanzada.fotos.modelo.FotoDTO;
import udistrital.avanzada.fotos.modelo.FotoResponse;

/**
 * FotoController se encarga del manejo y comunicacion de la API REST
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@RestController

public class FotoController {
    //Inyeccion automatica
    @Autowired
    private FotoService servicios;
    
    /**
     * Metodo para obtener una foto por su id
     * 
     * @param id
     * @return 
     */
    @RequestMapping(value = "/foto/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = {"http://localhost:8383","http://localhost:8090"})
    public ResponseEntity<FotoResponse> buscarFoto(@PathVariable Long id) {
        FotoResponse foto = servicios.getFoto(id);
        return ResponseEntity.ok(foto);
    }
    
    /**
     * Metodo para obtener las fotos por el id de un usuario
     * @param idUsuario
     * @return 
     */
    @RequestMapping(value = "/fotos/{idUsuario}", method = RequestMethod.GET)
    @CrossOrigin(origins = {"http://localhost:8383","http://localhost:8090"})
    public ResponseEntity<List<FotoResponse>> buscarFotos(@PathVariable Long idUsuario) {
        List<FotoResponse> fotos = servicios.getAllFotosUsuario(idUsuario);
        return ResponseEntity.ok(fotos);
    }
    
    /**
     * Metodo para eliminar un foto por su id
     * 
     * @param id
     * @return 
     */
    @RequestMapping(value = "/foto/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = {"http://localhost:8383","http://localhost:8090"})
    public ResponseEntity<Void> eliminarFoto(@PathVariable Long id) {
        boolean eliminado = servicios.deleteFoto(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Metodo para eliminar todas las fotos de un usuario
     * 
     * @param idUsuario
     * @return 
     */
    @RequestMapping(value = "/fotos/{idUsuario}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = {"http://localhost:8383"})
    public ResponseEntity<Void> eliminarFotosUsuario(@PathVariable Long idUsuario) {        
        if (servicios.deleteFotos(idUsuario)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Metodo para modificar una foto
     * 
     * @param id id de la foto
     * @param foto objeto a actulizar
     * @return 
     */
    @RequestMapping(value = "/foto/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = {"http://localhost:8383"})
    public ResponseEntity<FotoResponse> modificarFoto(@PathVariable Long id, @RequestBody FotoDTO foto) {
        FotoResponse res = servicios.modificarFoto(id, foto);
        if (res != null) {
            return ResponseEntity.status(201).body(res);
        }
        return ResponseEntity.status(500).body(res);
    }
    
    /**
     * Metodo para crear una foto
     * 
     * @param foto objeto a guardar
     * @return 
     */
    @RequestMapping(value = "/foto/", method = RequestMethod.POST)
    @CrossOrigin(origins = {"http://localhost:8383"})
    public ResponseEntity<FotoResponse> modificarFoto(@RequestBody FotoDTO foto) {
        FotoResponse res = servicios.crearFoto(foto);
        if (res != null) {
            return ResponseEntity.status(201).body(res);
        }
        return ResponseEntity.status(500).body(res);
    }
}
