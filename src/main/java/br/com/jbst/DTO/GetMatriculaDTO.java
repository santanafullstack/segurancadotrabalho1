package br.com.jbst.DTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import br.com.jbst.DTOs.GetFuncionarioDTOs;
import br.com.jbst.entities.map.Funcionario;
import lombok.Data;

@Data
public class GetMatriculaDTO {
	private UUID idMatricula;
    private Integer numeroMatricula;
	private Instant dataHoraCriacao;
	private String venda;
	private BigDecimal valor;
	private String status;
	private GetFuncionarioDTOs funcionario;
	private GetTurmasDTO turmas;

	
}
