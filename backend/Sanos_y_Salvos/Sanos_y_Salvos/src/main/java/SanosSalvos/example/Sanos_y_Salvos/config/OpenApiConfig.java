package SanosSalvos.example.Sanos_y_Salvos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sanos y Salvos API")
                        .version("1.0.0")
                        .description("Documentación del API del proyecto Sanos y Salvos para la gestión de mascotas y usuarios."));
    }
}
