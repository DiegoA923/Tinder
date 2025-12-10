package udistrital.avanzada.fotos.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import udistrital.avanzada.fotos.modelo.FotoDTO;
import udistrital.avanzada.fotos.modelo.FotoResponse;
import udistrital.avanzada.fotos.repository.FotoRepository;

/**
 * FotoService gestiona el acceso a los modelos de Foto
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
@Service
public class FotoService {
    //inyeccion automatica repositorio
    @Autowired
    private FotoRepository repositorio;
    //inyeccion automatica restTemplate para comunicacion con otros microservicios
    @Autowired
    private RestTemplate restTemplate;
    //inyeccion automatica ModelMapper para pasar de DTOs a Responses
    @Autowired
    private ModelMapper modelMapper;


    public FotoResponse crearFoto(FotoDTO foto) {
        FotoDTO f = repositorio.save(foto);
        FotoResponse res = null;
        if (f != null) {
            res = new FotoResponse(f.getId(), f.getUrl(), f.getUsuarioId(), f.getFechaCreacion());
        }
        //se retorna el juguete guardada
        return res;
    }

    /**
     * Metodo para 
     * @param idUsuario
     * @return 
     */
    public List<FotoResponse> getAllFotosUsuario(Long idUsuario) {
        List<FotoDTO> fotos = repositorio.findAllByUsuarioId(idUsuario);
        List<FotoResponse> fotosRes = new ArrayList();
        for (FotoDTO foto : fotos) {
            FotoResponse f = new FotoResponse(foto.getId(), foto.getUrl(), foto.getUsuarioId(), foto.getFechaCreacion());
            fotosRes.add(f);
        }
        return fotosRes;
    }
    
    /**
     * Metodo para obtener un foto por su id
     * 
     * @param id
     * @return 
     */
    public FotoResponse getFoto(Long id) {
        Optional<FotoDTO> foto = repositorio.findById(id);
        FotoResponse res = null;
        if (foto.isPresent()) {
            res = new FotoResponse(foto.get().getId(), foto.get().getUrl(), foto.get().getUsuarioId(), foto.get().getFechaCreacion());
        }
        return res;
    }

    /**
     * Metodo para borra un foto por su id
     * 
     * @param id
     * @return 
     */
    public boolean deleteFoto(Long id) {
        Optional<FotoDTO> foto = repositorio.findById(id);
        if (foto.isPresent()) {
            repositorio.delete(foto.get());
            return true;
        }
        return false;
    }
    
    /**
     * Metodo para borrar fotos de un usuario
     * @param idUser
     * @return 
     */
    public boolean deleteFotos(Long idUser) {
        return repositorio.deleteByUsuarioId(idUser) >= 0;
    }
    
    /**
     * Metodo para modificar una foto
     * 
     * @param id
     * @param nueva
     * @return 
     */
    public FotoResponse modificarFoto(Long id, FotoDTO nueva) {
        Optional<FotoDTO> foto = repositorio.findById(id);
        FotoResponse res = null;
        if (foto.isPresent()) {
            foto.get().setUrl(nueva.getUrl());
            FotoDTO guardado = repositorio.save(foto.get());
            res = new FotoResponse(guardado.getId(), guardado.getUrl(), guardado.getUsuarioId(), guardado.getFechaCreacion());
        }
        return res;
    }

}
