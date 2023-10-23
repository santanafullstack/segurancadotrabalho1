package br.com.jbst.controllers;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO.GetCursoDTO;
import br.com.jbst.DTO.GetInstrutorDTO;
import br.com.jbst.DTO.PostCursoDTO;
import br.com.jbst.DTO.PutCursoDTO;
import br.com.jbst.DTO.PutInstrutorDTO;
import br.com.jbst.services.CursoService;

@RestController
@RequestMapping(value = "/api/cursos")
public class CursoController {

	@Autowired
	CursoService  cursoService;
	
	@PostMapping
	public ResponseEntity<GetCursoDTO> CriarCurso(@RequestBody PostCursoDTO dto ){
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.criarCurso(dto));		

	}
	

	@PutMapping
	public ResponseEntity<GetCursoDTO> editarCurso(@RequestBody PutCursoDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(cursoService.editarCurso(dto));

	}

	@GetMapping
	public  ResponseEntity<List<GetCursoDTO>> ConsultarCursos() throws Exception{
		return  ResponseEntity
				.status(HttpStatus.OK)
				.body(cursoService.consultarCursos(toString()));
	                                 }

	@GetMapping("{id}")
	public  ResponseEntity<GetCursoDTO> consultarUmInstrutor(@PathVariable("id") UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(cursoService.consultarUmCurso(id));

	}

	@DeleteMapping("{id}")
	public ResponseEntity<GetCursoDTO> ExcluirCurso (@PathVariable("id") UUID id) throws Exception {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(cursoService.excluirCurso(id));

	}
}

