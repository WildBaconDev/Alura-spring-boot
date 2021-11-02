package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.service.TopicoService;

@RestController
@RequestMapping(path = "topicos")
public class TopicosController {
	
	@Autowired
	private TopicoService topicoService;
	
	@GetMapping
	public List<TopicoDto> listar(@RequestParam(name = "nomeCurso", required = false) String nomeCurso) {
		return topicoService.listarTopicos(nomeCurso);
	}
}
