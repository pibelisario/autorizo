package caixa.beneficente.autorizo;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lowagie.text.DocumentException;
import caixa.beneficente.autorizo.models.Associado;
import caixa.beneficente.autorizo.models.Compra;
import caixa.beneficente.autorizo.util.Relatorio;

@SpringBootApplication
public class AutorizoApplication {

	public static void main(String[] args) throws DocumentException, FileNotFoundException {
		SpringApplication.run(AutorizoApplication.class, args);

		System.out.println(new BCryptPasswordEncoder().encode("123"));

		Associado associado = new Associado(1L, "5370184", "Jão Vitor", "700.001.781-36",
				"Goiânia", "LIBERADO", 700.0, null);

		Compra c1 = new Compra(1L, 25.0, LocalDate.now(), associado);
		Compra c2 = new Compra(2L, 10.0, LocalDate.now(), associado);

		List<Compra> compras = new ArrayList<>();
		compras.add(c1);
		compras.add(c2);

		Relatorio relatorio = new Relatorio(associado, compras);
		relatorio.gerarCabecalho();
		relatorio.gerarCorpo();
		relatorio.imprimirRelaotrio();

	}

}
