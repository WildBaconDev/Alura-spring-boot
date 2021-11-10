package br.com.alura.forum.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

	@Autowired
	private CursoRepository repository;
	
	@Test
	void deveriaCarregarUmCursoAoBuscarPeloSeuNome() {
		String nomeCurso = "html 5";
		var curso = repository.findByNome(nomeCurso);
		
		assertThat(curso).isNotNull();
		assertThat(curso.getNome()).isEqualTo(nomeCurso);
		
	}

}
