package br.com.jbst.DTOs;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PostEvidenciasDTOs {

	private UUID idMatricula;
	private Instant dataHoraCriacao;
	private String evidencias;
	private String descricao;	


}
