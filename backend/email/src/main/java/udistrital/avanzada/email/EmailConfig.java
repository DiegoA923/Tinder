package udistrital.avanzada.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración general del microservicio de Email.
 * <p>
 * Incluye la habilitación de políticas CORS para permitir que el FrontEnd
 * (ubicado en un proyecto HTML externo) pueda consumir los endpoints encargados
 * del envío de correos.
 * </p>
 *
 * @author Diego
 * @version 1.0
 * @since 2025-12-09
 */
@Configuration
public class EmailConfig {

    /**
     * Configuración global de CORS, permitiendo acceso desde cualquier origen.
     *
     * @return Configuración de CORS para el proyecto.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }
}
