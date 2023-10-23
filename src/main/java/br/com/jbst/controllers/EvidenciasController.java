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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.jbst.DTOs.GetEvidenciasDTOs;
import br.com.jbst.DTOs.GetProficienciaDTOs;
import br.com.jbst.DTOs.PostEvidenciasDTOs;
import br.com.jbst.DTOs.PutEvidenciasDTOs;
import br.com.jbst.services.EvidenciasServices;

@RestController
@RequestMapping(value = "/api/evidencias")
public class EvidenciasController {

	@Autowired
	EvidenciasServices  evidenciasServices;
	
	@PostMapping
	public ResponseEntity<GetEvidenciasDTOs> CriarEvidencias(@RequestBody PostEvidenciasDTOs dto ){
		return ResponseEntity.status(HttpStatus.CREATED).body(evidenciasServices.criarEvidencias(dto));		

	}
	

	@PutMapping
	public ResponseEntity<GetEvidenciasDTOs> editarEvidencias(@RequestBody PutEvidenciasDTOs dto) {
		return ResponseEntity.status(HttpStatus.OK).body(evidenciasServices.editarEvidencias(dto));

	}

	@GetMapping
	public  ResponseEntity<List<GetEvidenciasDTOs>> ConsultarEvidencias() throws Exception{
		return  ResponseEntity
				.status(HttpStatus.OK)
				.body(evidenciasServices.consultarEvidencias(toString()));
	                                 }

	@GetMapping("{id}")
	public  ResponseEntity<GetEvidenciasDTOs> consultarUmaProficiencia(@PathVariable("id") UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(evidenciasServices.consultarUmaEvidencia(id));

	}
	
	
	@PutMapping(value = "incluir-evidencia", consumes = { "multipart/form-data" })
	public ResponseEntity<GetEvidenciasDTOs> IncluirEvidencias(@RequestParam("id") UUID id,
			@RequestParam("incluirevidencias") MultipartFile incluirevidencias) throws Exception {

		// capturando o tipo do arquivo
		String tipo = incluirevidencias.getContentType();

		// verificando se o formato é válido (JPG, JPEG, PNG)
		if (tipo.equals("image/jpg") || tipo.equals("application/pdf")  || tipo.equals("image/jpeg") || tipo.equals("image/png")) {
			// atualizando a foto do produto e retornando a resposta
			GetEvidenciasDTOs result = evidenciasServices.incluirEvidencias(id, incluirevidencias.getBytes());
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	

	@DeleteMapping("{id}")
	public ResponseEntity<GetEvidenciasDTOs> ExcluirEvidencias (@PathVariable("id") UUID id) throws Exception {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(evidenciasServices.excluirEvidencias(id));

	}
}
