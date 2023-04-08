package caixa.beneficente.autorizo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AutorizoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutorizoApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}

