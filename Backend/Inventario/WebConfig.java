package com.example.Inventario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configura CORS para los endpoints que comienzan con /api/
        registry.addMapping("/*") // Permitir CORS para todas las rutas que comienzan con /api/
                .allowedOrigins("http://127.0.0.1:5500") // Permitir solicitudes desde este origen
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("") // Permitir todos los encabezados
                .allowCredentials(true); // Permitir credenciales (cookies, autorización)
    }
}
