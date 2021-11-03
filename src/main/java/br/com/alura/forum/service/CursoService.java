package br.com.alura.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	public Curso consultarCursoPorNome(String nomeCurso) {
		return cursoRepository.findByNome(nomeCurso);
	}
}
