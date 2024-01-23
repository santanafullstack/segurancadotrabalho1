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

import br.com.jbst.DTO.GetPedidosDTO;
import br.com.jbst.DTO.PostPedidosDTO;
import br.com.jbst.DTO.PutPedidosDTO;
import br.com.jbst.DTO.RelatorioPedidosDTO;
import br.com.jbst.services.PedidosService;

@RestController
@RequestMapping (value = "/api/pedidos-de-compras")
public class PedidosController {
	
	
	@Autowired
	PedidosService  pedidosService;
	
	@PostMapping
	public ResponseEntity<GetPedidosDTO> CriarCurso(@RequestBody PostPedidosDTO dto ) throws Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidosService.criarPedidos(dto));		

	}
	

	@PutMapping
	public ResponseEntity<GetPedidosDTO> editarPedidos(@RequestBody PutPedidosDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(pedidosService.editarPedidos(dto));

	}

	@GetMapping
	public  ResponseEntity<List<GetPedidosDTO>> ConsultarPedidos() throws Exception{
		return  ResponseEntity
				.status(HttpStatus.OK)
				.body(pedidosService.consultarPedidos());
	                                 }

	@GetMapping("{id}")
	public  ResponseEntity<GetPedidosDTO> consultarUmPedidos(@PathVariable("id") UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(pedidosService.consultarPedidos(id));

	}

	@DeleteMapping("{id}")
	public ResponseEntity<GetPedidosDTO> ExcluirPedido (@PathVariable("id") UUID id) throws Exception {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(pedidosService.excluirPedidos(id));

	}
	
	 @GetMapping("/calcularTotal/{id}")
		public  ResponseEntity<RelatorioPedidosDTO> calcularEAtualizarTotal(@PathVariable("id") UUID id){
			return ResponseEntity.status(HttpStatus.OK).body(pedidosService.calcularEAtualizarTotal(id));

		}

	
	  

	}


