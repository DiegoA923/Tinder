package udistrital.avanzada.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar el envío de correos.
 * <p>
 * Expuesto para ser consumido por otros microservicios mediante API REST.
 * </p>
 *
 * @author Diego
 * @version 1.0
 * @since 2025-12-09
 */
@RestController
@RequestMapping("/email")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService service;

    /**
     * Endpoint para enviar un correo.
     *
     * @param dto JSON con los datos del correo.
     * @return Resultado del envío.
     */
    @PostMapping("/enviar")
    public EmailResponse enviar(@RequestBody EmailDTO dto) {
        return service.enviarCorreo(dto);
    }
}
