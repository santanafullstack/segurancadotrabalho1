package br.com.jbst.DTOs;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class GetEvidenciasDTOs {

	private UUID idMatricula;
	private UUID idEvidencias;
	private Instant dataHoraCriacao;
	private String evidencias;
	private String descricao;	
	private byte[] inserir_evidencias;
}
