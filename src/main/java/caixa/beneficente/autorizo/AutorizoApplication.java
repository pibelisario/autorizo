package caixa.beneficente.autorizo;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lowagie.text.DocumentException;

@SpringBootApplication
public class AutorizoApplication {

	public static void main(String[] args) throws DocumentException, FileNotFoundException {
		SpringApplication.run(AutorizoApplication.class, args);

		// System.out.println(new BCryptPasswordEncoder().encode("123"));

	}

}
