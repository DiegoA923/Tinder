package udistrital.avanzada.swipes;
/**
 * SwipesConfigclase que concentra configuracion especifica de la aplicacion
 *
 * @author mauricio
 * @version 1.0
 * @since 2025-12-09
 */
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwipesConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    //metodo para configurar el acceso al proyecto desde otros dominio o puertos
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/matches**").allowedOrigins("http://localhost:8383","http://localhost:8090").allowedMethods("*").allowedHeaders("*");
            }
        };
    }
}