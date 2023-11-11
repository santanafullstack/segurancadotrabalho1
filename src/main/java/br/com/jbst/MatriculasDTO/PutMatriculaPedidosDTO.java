package br.com.jbst.MatriculasDTO;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class PutMatriculaPedidosDTO {
	private UUID idMatricula;
	private UUID idTurmas;
	private UUID idFuncionario;
	private UUID idPedidos;
	private String venda;
	private BigDecimal valor;
	private String status;
	private String tipo_de_pagamento;

}
