package br.com.jbst.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTOs.PostCertificadoDTO;
import br.com.jbst.DTOs.PutCertificadoDTO;

@RestController
@RequestMapping(value = "/api/certificado")
public class CertificadoControllers {
	@PostMapping
	public void CriarCertificado(@RequestBody PostCertificadoDTO dto) {
    
	}

	@PutMapping
	public void EditarCertificado(@RequestBody PutCertificadoDTO dto) {

	}

	@GetMapping()
	public void ConsultarCertificados() {

	}

	@GetMapping("{id}")
	public void ConsultarUmCertificado(@PathVariable UUID id) {

	}

	@DeleteMapping("{id}")
	public void ExcluirCertificado(@PathVariable UUID id) {

	}
}
