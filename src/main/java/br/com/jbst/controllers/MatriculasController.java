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
import br.com.jbst.DTO.GetMatriculaDTO;
import br.com.jbst.DTO.GetPedidosDTO;
import br.com.jbst.DTO.PostMatriculaDTO;
import br.com.jbst.DTO.PutCursoDTO;
import br.com.jbst.DTO.PutMatriculaDTO;
import br.com.jbst.services.MatriculasService;

@RestController
@RequestMapping (value = "/api/matriculas")
public class MatriculasController {
	
	@Autowired
	MatriculasService matriculaService;
	


	
	@PostMapping("/criarMatricula/{idPedidos}")
	public ResponseEntity<GetMatriculaDTO> criarMatricula(@PathVariable UUID idPedidos, @RequestBody PostMatriculaDTO dto) throws Exception {
	    return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.criarMatriculas(idPedidos, dto));
	}




	@PutMapping
	public ResponseEntity<GetMatriculaDTO> editarMatriculas(@RequestBody PutMatriculaDTO dto) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(matriculaService. editarMatricula(dto));

	}

	@GetMapping
	public  ResponseEntity<List<GetMatriculaDTO>> ConsultarMatriculas() throws Exception{
		return  ResponseEntity
				.status(HttpStatus.OK)
				.body(matriculaService.consultarMatriculas(toString()));
	                                 }
	@GetMapping("{id}")
	public  ResponseEntity<GetMatriculaDTO> consultarUmaMatricula(@PathVariable("id") UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(matriculaService.consultarUmaMatricula(id));

	}


	@DeleteMapping("{id}")
	public void ExcluirMatricula(@PathVariable UUID id) {

	}
}
