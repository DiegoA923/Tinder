package udistrital.avanzada.usuarios.controlador;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import udistrital.avanzada.usuarios.modelo.UsuarioDTO;
import udistrital.avanzada.usuarios.modelo.UsuarioResponse;

/**
 * Controlador REST para gestionar las operaciones del usuario.
 * <p>
 * Exponen los endpoints accesibles por el FrontEnd mediante Fetch API.
 * </p>
 *
 * @author Diego
 * @version 1.0
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    /**
     * Endpoint para registrar un usuario. Recibe JSON enviado por el FrontEnd.
     */
    @PostMapping("/registrar")
    public UsuarioResponse registrar(@RequestBody UsuarioDTO usuario) {
        return service.registrarUsuario(usuario);
    }

    /**
     * Endpoint para buscar usuario por nickname.
     */
    @GetMapping("/buscar/{nickname}")
    public UsuarioResponse buscar(@PathVariable String nickname) {
        return service.buscarPorNickname(nickname);
    }

    /**
     * Endpoint para iniciar sesión. Recibe JSON: {"nickname":"...",
     * "password":"..."}
     * @param datos
     * @return 
     */
    @PostMapping("/login")
    public UsuarioResponse login(@RequestBody Map<String, String> datos) {

        String nickname = datos.get("nickname");
        String password = datos.get("password");

        UsuarioDTO usuario = service.buscarDTO(nickname);

        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado");
        }

        if (!usuario.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        return service.buscarPorNickname(nickname); // Devuelve respuesta segura
    }
}
