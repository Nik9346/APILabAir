package it.labair;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ApiLabairApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiLabairApplication.class, args);
	}

	@Bean
	WebMvcConfigurer corsConfigurer() { //rimossa la dicitura public controllare se funziona uguale la chiamata
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200")
						.allowedMethods("GET","POST","PUT","DELETE","OPTIONS").allowCredentials(true);
			}
		};
	};

}
