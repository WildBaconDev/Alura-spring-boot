package br.com.alura.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.repository.TopicoRepository;

@Service
public class TopicoService {

	@Autowired
	private TopicoRepository topicoDao;
	
//	public List<TopicoDto> listarTopicos() {
//		return TopicoDto.converter( topicoDao.findAll() );
//	}
	
	public List<TopicoDto> listarTopicos(String nomeCurso) {
		if (nomeCurso == null) {
			return TopicoDto.converter( topicoDao.findAll() );
		}
		
		return TopicoDto.converter( topicoDao.findByCursoNome(nomeCurso) );
	}
	
}
