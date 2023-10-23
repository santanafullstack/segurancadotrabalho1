package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class ListaDePresenca {

	
	private UUID idListadepresenca;
	private Instant datahoracriacao;
	private String listadepresenca;
}
