package udistrital.avanzada.fotos;

/**
 * FotosConfig clase que concentra configuracion especifica de la aplicacion
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FotosConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
