package br.com.jbst.MatriculasDTO;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class PostMatriculaFaturamentoPjDTO {
	private UUID idTurmas;
	private UUID usuario;
	private UUID funcionario;
	private UUID faturamento;
	private String venda;
	private BigDecimal valor;
	private String status;
	private String tipo_de_pagamento;
}
