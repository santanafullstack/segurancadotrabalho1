package br.com.jbst.DTO;

import java.time.Instant;


import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostMatriculaDTO {
	
	private UUID turmas;
	private UUID funcionario;
	private UUID pessoafisica;
	private UUID faturamento;
	private UUID pedidos;
	private Instant dataHoraCriacao;
	private String venda;
	private String valor;
	private String status;
	
	
}
