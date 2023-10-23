package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PutFormacaoDTO {

	private UUID idInstrutor;
	private UUID idFormacao;
	private Instant dataHoraCriacao;
	private String formacao;
	private String conselho;
	private String registro;
	private String estado;
}
