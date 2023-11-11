package br.com.jbst.MatriculasDTO;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class PutMatriculaFaturamentoPjDTO {

	
	private UUID idMatricula;
	private UUID idFuncionario;
	private UUID idTurmas;
	private UUID idfaturamento;
	private String venda;
	private BigDecimal valor;
	private String status;
	private String tipo_de_pagamento;

}
