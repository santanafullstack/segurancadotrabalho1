package br.com.jbst.MatriculasDTO;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class PostMatriculaPedidosDTO {

	private UUID turmas;
	private UUID funcionario;
	private UUID pedidos;
	private String venda;
	private BigDecimal valor;
	private String status;
}
