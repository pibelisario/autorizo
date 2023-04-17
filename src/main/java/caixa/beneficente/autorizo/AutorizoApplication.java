package caixa.beneficente.autorizo;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lowagie.text.DocumentException;

import caixa.beneficente.autorizo.models.Associado;
import caixa.beneficente.autorizo.models.Compra;
import caixa.beneficente.autorizo.models.Usuario;
import caixa.beneficente.autorizo.repositories.CompraRepository;
import caixa.beneficente.autorizo.util.RelatorioMensal;

@SpringBootApplication
public class AutorizoApplication {

	public static void main(String[] args) throws DocumentException, FileNotFoundException {
		SpringApplication.run(AutorizoApplication.class, args);

		System.out.println(new BCryptPasswordEncoder().encode("123"));

		Associado associado = new Associado();
		associado.setId(1L);
		associado.setNome("Norival Braga Teixeira");
		associado.setRg("23.697.356-3");
		associado.setCpf("244.562.738-95");
		associado.setCidade("Goiania");
		associado.setSituacao("LIBERADO");
		associado.setLimite(800.0);

		Compra c1 = new Compra(1L, 150.0, LocalDate.now(), null, associado);
		Compra c2 = new Compra(1L, 100.0, LocalDate.now(), null, associado);

		List<Compra> comprasMensal = new ArrayList();
		comprasMensal.add(c1);
		comprasMensal.add(c2);

		RelatorioMensal relatorioMensal = new RelatorioMensal();
		relatorioMensal.gerarCabecalho();
		relatorioMensal.gerarCorpo();
		relatorioMensal.imprimirRelaotrio();

	}

}
