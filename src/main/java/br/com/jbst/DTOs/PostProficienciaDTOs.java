package br.com.jbst.DTOs;

import java.time.Instant;
import java.util.UUID;

import br.com.jbst.entities.Instrutor;
import lombok.Data;

@Data
public class PostProficienciaDTOs {

	private UUID idinstrutor;
	private String proficiencia;
	private String descricao;	

}
