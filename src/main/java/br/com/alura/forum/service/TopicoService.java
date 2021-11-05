package br.com.alura.forum.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.dto.AtualizacaoTopicoForm;
import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import javassist.NotFoundException;

@Service
public class TopicoService {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Cacheable(value = "listaDeTopicos")
	public Page<TopicoDto> listarTopicos(String nomeCurso, Pageable paginacao) {		
		if (nomeCurso == null) {
			return TopicoDto.converter( topicoRepository.findAll(paginacao) );
		}
		
		return TopicoDto.converter( topicoRepository.findByCursoNome(nomeCurso, paginacao) );
	}

	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public Topico salvar(TopicoForm topicoForm) {
		Curso curso = cursoService.consultarCursoPorNome(topicoForm.getNomeCurso());
		Topico topico = new Topico(topicoForm.getTitulo(), topicoForm.getMensagem(), curso);
		
		return topicoRepository.save(topico);
	}

	public Optional<Topico> consultarTopicoPorId(Long id) {
		return topicoRepository.findById(id);
	}

	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public Topico atualizar(AtualizacaoTopicoForm topicoForm) throws NotFoundException {
		Optional<Topico> registro = topicoRepository.findById(topicoForm.getId());
		
		if (registro.isPresent()) {
			Topico topico = registro.get();
			topico.setTitulo( topicoForm.getTitulo() );
			topico.setMensagem( topicoForm.getMensagem() );			
			return topicoRepository.save(topico);
		}
		
		throw new NotFoundException("Registro não encontrado!");
	}

	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public void deletar(Long id) throws EmptyResultDataAccessException {
		topicoRepository.deleteById(id);			
	}
	
}
