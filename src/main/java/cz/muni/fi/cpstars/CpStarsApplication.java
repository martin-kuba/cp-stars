package cz.muni.fi.cpstars;

import cz.muni.fi.cpstars.rest.controllers.Paths;
import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableJpaRepositories(repositoryBaseClass = SimpleJpaRepository.class)
//@EnableJpaRepositories(basePackages = "cz.muni.fi.cpstars.dal.interfaces", repositoryImplementationPostfix = "CustomImpl")
@EnableJpaRepositories
@SpringBootApplication
public class CpStarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CpStarsApplication.class, args
		);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry.addMapping(Paths.ALL_APPLICATION_PATHS).allowedOrigins(Paths.CROSS_ORIGIN_FRONTEND);
			}
		};
	}

}
