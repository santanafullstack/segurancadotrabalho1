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

import br.com.jbst.DTOs.PostListaDePresencaDTO;
import br.com.jbst.DTOs.PutListaDePresencaDTO;



@RestController
@RequestMapping(value = "/api/listadepresenca")
public class ListaDePresencaControllers {
	@PostMapping
	public void CriarListaDePresenca(@RequestBody PostListaDePresencaDTO dto) {
    
	}

	@PutMapping
	public void EditarListaDePresenca(@RequestBody PutListaDePresencaDTO dto) {

	}

	@GetMapping()
	public void ConsultarListaDePresenca() {

	}

	@GetMapping("{id}")
	public void ConsultarUmaListaDePresenca(@PathVariable UUID id) {

	}

	@DeleteMapping("{id}")
	public void ExcluirListaDePresenca(@PathVariable UUID id) {

	}
}
