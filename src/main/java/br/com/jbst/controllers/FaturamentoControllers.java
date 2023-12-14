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

import br.com.jbst.DTO.RelatorioFaturamentoDTO;
import br.com.jbst.DTOs.GetFaturamentoDTO;
import br.com.jbst.DTOs.PostFaturamentoDTO;
import br.com.jbst.DTOs.PutFaturamentoDTO;
import br.com.jbst.services.FaturamentoService;

@RestController
@RequestMapping(value = "/api/faturamento")
public class FaturamentoControllers {
	
	
	@Autowired
	FaturamentoService  faturamentoService;
	
	@PostMapping
	public ResponseEntity<GetFaturamentoDTO> CriarFaturamento(@RequestBody PostFaturamentoDTO dto ){
		return ResponseEntity.status(HttpStatus.CREATED).body(faturamentoService.criarFaturamento(dto));		

	}
	

	@PutMapping
	public ResponseEntity<GetFaturamentoDTO> editarFaturamento(@RequestBody PutFaturamentoDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(faturamentoService.editarFaturamento(dto));

	}

	@GetMapping
	public  ResponseEntity<List<GetFaturamentoDTO>> ConsultarFaturamento() throws Exception{
		return  ResponseEntity
				.status(HttpStatus.OK)
				.body(faturamentoService.consultarFaturamentos(toString()));
	                                 }

	@GetMapping("{id}")
	public  ResponseEntity<GetFaturamentoDTO> consultarUmFaturamento(@PathVariable("id") UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(faturamentoService.consultarUmFaturamento(id));
	}
	
	 @GetMapping("/calcularTotal/{id}")
		public  ResponseEntity<RelatorioFaturamentoDTO> calcularEAtualizarTotal(@PathVariable("id") UUID id){
			return ResponseEntity.status(HttpStatus.OK).body(faturamentoService.calcularEAtualizarTotal(id));

		}

	@DeleteMapping("{id}")
	public ResponseEntity<GetFaturamentoDTO> ExcluirFaturamento (@PathVariable("id") UUID id) throws Exception {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(faturamentoService.excluirFaturamento(id));

	}
}