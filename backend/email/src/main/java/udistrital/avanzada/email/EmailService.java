package udistrital.avanzada.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado del envío de correos electrónicos.
 * <p>
 * Implementa la lógica de negocio asociada al envío de emails usando
 * JavaMailSender de Spring Boot.
 * </p>
 *
 * @author Diego
 * @version 1.0
 * @since 2025-12-09
 */
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    /**
     * Envía un correo simple con los datos recibidos.
     *
     * @param dto Objeto con la información del correo.
     * @return EmailResponse con el resultado del envío.
     */
    public EmailResponse enviarCorreo(EmailDTO dto) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(dto.getDestinatario());
            email.setSubject(dto.getAsunto());
            email.setText(dto.getMensaje());

            mailSender.send(email);

            return new EmailResponse("Correo enviado correctamente", true);
        } catch (Exception e) {
            return new EmailResponse("Error al enviar correo: " + e.getMessage(), false);
        }
    }
}
