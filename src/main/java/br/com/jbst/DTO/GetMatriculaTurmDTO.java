package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import br.com.jbst.DTOs.GetFuncionarioDTOs;
import lombok.Data;


@Data
public class GetMatriculaTurmDTO {
	private UUID idMatricula;
    private Integer numeroMatricula;
	private Instant dataHoraCriacao;
	private String venda;
	private String valor;
	private String status;
	private GetFuncionarioDTOs funcionario;
}
