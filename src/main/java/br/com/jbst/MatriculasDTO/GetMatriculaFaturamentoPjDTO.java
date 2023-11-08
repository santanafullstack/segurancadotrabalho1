package br.com.jbst.MatriculasDTO;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class GetMatriculaFaturamentoPjDTO {
	private UUID idMatricula;
	private UUID idturmas;
	private UUID funcionario;
	private UUID faturamento;
	private String venda;
	private BigDecimal valor;
	private String status;}
