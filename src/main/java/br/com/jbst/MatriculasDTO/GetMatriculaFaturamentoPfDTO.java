package br.com.jbst.MatriculasDTO;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class GetMatriculaFaturamentoPfDTO {

	private UUID idMatricula;
	private UUID turmas;
	private UUID funcionario;
	private UUID faturamentopf;
	private String venda;
	private BigDecimal valor;
	private String status;
}
