package springboot_kurs_controller_service_repository_basic_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		csrf().disable().
		authorizeRequests().
		antMatchers("/","index","/css/*","/js/*").permitAll().   // Bu satirin amaci buraya ekledigimiz sayfalara izin verilmesi (buralarda sifre istenmeyecek) olayi.
		anyRequest().
		authenticated().
		and().
		httpBasic(); //Basic authentication istiyorum anlamina gelir.
	}

	@Override
	@Bean //asagidaki metodumuz bir obje döndürdügü icin @Bean anotasyonunu koyduk. @Configuration kullanmadan @Bean kullanilmaz
	protected UserDetailsService userDetailsService() {
	
	UserDetails student= User.builder().username("hakan").password(passwordEncoder.encode("1978")).roles("STUDENT").build();
	UserDetails admin= User.builder().username("aliihsan").password(passwordEncoder.encode("2007")).roles("ADMIN").build();
	
	return new InMemoryUserDetailsManager(student, admin);
	}
	
	// passwordEncoder.encode("sifre") seklinde sifreler encoding yapilir
 	
	

}
