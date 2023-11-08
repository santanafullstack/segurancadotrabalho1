package br.com.jbst.MatriculasDTO;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class PutMatriculaFaturamentoPjDTO {

	
	private UUID idMatricula;
	private UUID funcionario;
	private UUID turmas;
	private UUID faturamento;
	private String venda;
	private BigDecimal valor;
	private String status;
}
