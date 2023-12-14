package br.com.jbst.DTOs;


import java.util.UUID;

import lombok.Data;

@Data
public class PostEvidenciasDTOs {

	private UUID idMatricula;
	private String evidencias;
	private String descricao;	


}
