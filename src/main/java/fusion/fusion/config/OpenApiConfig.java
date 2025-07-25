package fusion.fusion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST de Proyecto SafeZone")
                        .version("1.0")
                        .description("Documentación generada automáticamente con SpringDoc OpenAPI Starter WebMVC UI")
                        .contact(new Contact()
                                .name("SafeZone")
                                .email("SafeZoneSupport@Safezone.com")
                                .url("http://localhost:5173/")
                        )
                );
    }




}
