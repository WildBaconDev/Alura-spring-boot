package br.com.alura.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

@Service
public class TopicoService {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private TopicoRepository topicoRepository;
	
//	public List<TopicoDto> listarTopicos() {
//		return TopicoDto.converter( topicoDao.findAll() );
//	}
	
	public List<TopicoDto> listarTopicos(String nomeCurso) {
		if (nomeCurso == null) {
			return TopicoDto.converter( topicoRepository.findAll() );
		}
		
		return TopicoDto.converter( topicoRepository.findByCursoNome(nomeCurso) );
	}

	public Topico salvar(TopicoForm topicoForm) {
		Curso curso = cursoService.consultarCursoPorNome(topicoForm.getNomeCurso());
		Topico topico = new Topico(topicoForm.getTitulo(), topicoForm.getMensagem(), curso);
		
		return topicoRepository.save(topico);
	}
	
}
