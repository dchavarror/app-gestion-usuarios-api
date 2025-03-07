package co.com.dxc.gestion.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AppGestionUsuariosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppGestionUsuariosApiApplication.class, args);
	}

}
