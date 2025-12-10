package udistrital.avanzada.matches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MatchesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchesApplication.class, args);
    }

    @Bean
    //metodo para configurar el acceso al proyecto desde otros dominio o puertos
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/matches**").allowedOrigins("http://localhost:8383","http://localhost:8090","http://localhost:8095").allowedMethods("*").allowedHeaders("*");
            }
        };
    }

}
