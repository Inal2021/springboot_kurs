package springboot_kurs_controller_service_repository_basic_authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class PasswordConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10); // Buradaki 10 sifreleme seviyesini gösteriyor. 15 de olabilir 8 de...
    }
	
}
