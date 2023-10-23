package br.com.jbst.DTOs;

import java.time.Instant;
import java.util.UUID;

import br.com.jbst.entities.map.Funcionario;
import lombok.Data;


@Data
public class GetFuncaoDTOs {

	
	private UUID idFuncao;
    private Instant dataHoraCriacao;
	private String funcao;
	private String cbo;
    private String descricao;
}
