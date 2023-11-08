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
import br.com.jbst.MatriculasDTO.GetMatriculaFaturamentoPfDTO;
import br.com.jbst.MatriculasDTO.GetMatriculaFaturamentoPjDTO;
import br.com.jbst.MatriculasDTO.GetMatriculaPedidosDTO;
import br.com.jbst.MatriculasDTO.PostMatriculaFaturamentoPfDTO;
import br.com.jbst.MatriculasDTO.PostMatriculaFaturamentoPjDTO;
import br.com.jbst.MatriculasDTO.PostMatriculaPedidosDTO;
import br.com.jbst.MatriculasDTO.PutMatriculaFaturamentoPfDTO;
import br.com.jbst.MatriculasDTO.PutMatriculaFaturamentoPjDTO;
import br.com.jbst.MatriculasDTO.PutMatriculaPedidosDTO;
import br.com.jbst.services.MatriculasService;

@RestController
@RequestMapping (value = "/api/matriculas")
public class MatriculasController {
	
	@Autowired
	MatriculasService matriculaService;
	

	//1
	@PostMapping("/criar-matriculas-faturamento-pj")
	public ResponseEntity<GetMatriculaFaturamentoPjDTO> criarMatriculaFaturamentoPj( @RequestBody PostMatriculaFaturamentoPjDTO dto) throws Exception {
	    return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.criarMatriculasFaturamentoPj( dto));
	}
	
	//2
	@PutMapping("/editar-matriculas-faturamento-pj")
	public ResponseEntity<GetMatriculaFaturamentoPjDTO> editarMatriculas(@RequestBody PutMatriculaFaturamentoPjDTO dto) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(matriculaService.editarMatriculaPj(dto));

	}
	
	
	//3
	@PostMapping("/criar-matriculas-faturamento-pf")
	public ResponseEntity<GetMatriculaFaturamentoPfDTO> criarMatriculaFaturamentoPf( @RequestBody PostMatriculaFaturamentoPfDTO dto) throws Exception {
	    return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.criarMatriculasFaturamentoPf( dto));
	}
	
	//4
	@PutMapping("/editar-matriculas-faturamento-pf")
	public ResponseEntity<GetMatriculaFaturamentoPfDTO> editarMatriculaspf(@RequestBody PutMatriculaFaturamentoPfDTO dto) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(matriculaService.editarMatriculaPf(dto));

	}
	
	//5
	@PostMapping("/criarMatricula-pedidos/{idPedidos}")
	public ResponseEntity<GetMatriculaPedidosDTO> criarMatricula(@PathVariable UUID idPedidos, @RequestBody PostMatriculaPedidosDTO  dto) throws Exception {
	    return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.criarMatriculasPedidos(idPedidos, dto));
	}

	//6
	@PutMapping("/editarMatricula-pedidos")
	public ResponseEntity<GetMatriculaPedidosDTO> editarMatriculas(@RequestBody PutMatriculaPedidosDTO dto) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(matriculaService.editarMatriculaPedidos(dto));

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
