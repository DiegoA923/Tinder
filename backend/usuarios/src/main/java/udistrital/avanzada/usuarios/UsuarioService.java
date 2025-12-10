package udistrital.avanzada.usuarios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.client.RestTemplate;

/**
 * Servicio encargado de la lógica de negocio asociada al usuario.
 * <p>
 * Procesa el registro, transformación de datos y consultas.
 * </p>
 *
 * @author Diego
 * @version 1.0
 * @since 2025-12-09
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    /**
     * Busca el DTO completo (incluye contraseña).
     */
    public UsuarioDTO buscarDTO(String nickname) {
        return repository.findByNickname(nickname);
    }

    /**
     * Registra un nuevo usuario y retorna su información visible.
     *
     * @param usuario datos del usuario enviados desde el FrontEnd
     * @return UsuarioResponse con información segura
     */
    public UsuarioResponse registrarUsuario(UsuarioDTO usuario) {

        validarPassword(usuario.getPassword());

        UsuarioDTO guardado = repository.save(usuario);


        enviarCorreoRegistro(guardado); // Enviar correo de confirmacion

        return new UsuarioResponse(
                guardado.getId(),
                guardado.getNickname(),
                guardado.getNombre(),
                guardado.getEdad(),
                guardado.getCiudad(),
                guardado.getCorreo(),
                guardado.getFechaRegistro(),
                guardado.getDescripcion(),
                guardado.getGenero(),
                guardado.getFotoPerfil()
        );

    }

    /**
     * Busca un usuario por su nickname.
     *
     * @param nickname nickname del usuario
     * @return UsuarioResponse o null si no existe
     */
    public UsuarioResponse buscarPorNickname(String nickname) {

        UsuarioDTO u = repository.findByNickname(nickname);

        if (u == null) {
            return null;
        }

        return toResponse(u);
    }

    private void enviarCorreoRegistro(UsuarioDTO usuario) {

        try {
            RestTemplate rest = new RestTemplate();

            Map<String, String> email = new HashMap<>();
            email.put("destinatario", usuario.getCorreo());
            email.put("asunto", "¡Bienvenido a Tinder UD!");
            email.put("mensaje", "Hola " + usuario.getNombre()
                    + ", tu registro fue exitoso. ¡Bienvenido!");

            rest.postForObject("http://localhost:8091/email/enviar", email, String.class);

        } catch (Exception e) {
            System.out.println("Error enviando correo: " + e.getMessage());
        }
    }

    /**
     * Convierte un UsuarioDTO en UsuarioResponse.
     */
    private UsuarioResponse toResponse(UsuarioDTO dto) {
        UsuarioResponse r = new UsuarioResponse();

        r.setId(dto.getId());
        r.setNickname(dto.getNickname());
        r.setNombre(dto.getNombre());
        r.setEdad(dto.getEdad());
        r.setCiudad(dto.getCiudad());
        r.setCorreo(dto.getCorreo());
        r.setFechaRegistro(dto.getFechaRegistro());
        r.setDescripcion(dto.getDescripcion());
        r.setGenero(dto.getGenero());
        r.setFotoPerfil(dto.getFotoPerfil());

        return r;
    }

    private void validarPassword(String password) {

        if (password.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener mínimo 8 caracteres");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Debe contener al menos UNA letra mayúscula");
        }

        if (!password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Debe contener al menos UNA letra minúscula");
        }

        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Debe contener al menos UN número");
        }

        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new IllegalArgumentException("Debe contener al menos UN carácter especial");
        }
    }

}
