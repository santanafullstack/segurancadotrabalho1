package br.com.jbst.MatriculasDTO;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class PostMatriculaFaturamentoPfDTO {
	private UUID idTurmas;
	private UUID idpessoafisica;
	private UUID idfaturamentopf;
	private String venda;
	private BigDecimal valor;
	private String status;
}