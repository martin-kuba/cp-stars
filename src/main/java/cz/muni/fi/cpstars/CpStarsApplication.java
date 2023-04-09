package cz.muni.fi.cpstars;

import cz.muni.fi.cpstars.rest.controllers.Paths;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import lombok.NonNull;
import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableJpaRepositories(repositoryBaseClass = SimpleJpaRepository.class)
//@EnableJpaRepositories(basePackages = "cz.muni.fi.cpstars.dal.interfaces", repositoryImplementationPostfix = "CustomImpl")
@OpenAPIDefinition(
		servers = {
				@Server(
						url = "http://{server}:${server.port}",
						description = "Default Server URL",
						variables = {
								@ServerVariable(
										name = "server",
										description = "Server URL address",
										defaultValue = "147.251.21.135"
								)
						}
				)
		},
		info = @Info(
			title = "Chemically Peculiar Stars Database OpenAPI definitions",
			version = "0.0.1"
		)
)
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
				registry.addMapping(Paths.ALL_APPLICATION_PATHS);
			}
		};
	}

}
