package caixa.beneficente.autorizo;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lowagie.text.DocumentException;

import caixa.beneficente.autorizo.util.RelatorioMensal;

@SpringBootApplication
public class AutorizoApplication {

	public static void main(String[] args) throws DocumentException, FileNotFoundException {
		SpringApplication.run(AutorizoApplication.class, args);

		System.out.println(new BCryptPasswordEncoder().encode("123"));

		RelatorioMensal relatorioMensal = new RelatorioMensal(null, null, null);
		relatorioMensal.gerarCabecalho();
		relatorioMensal.imprimirRelaotrio();

	}

}
