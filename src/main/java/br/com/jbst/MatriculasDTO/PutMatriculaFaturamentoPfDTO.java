package br.com.jbst.MatriculasDTO;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class PutMatriculaFaturamentoPfDTO {
	
	
	private UUID idMatricula;
	private UUID idpessoafisica;
	private UUID idTurmas;
	private UUID idfaturamentopf;
	private String venda;
	private BigDecimal valor;
	private String status;
	private String tipo_de_pagamento;

}
