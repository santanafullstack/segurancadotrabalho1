package br.com.jbst.DTO;

import java.time.Instant;

import lombok.Data;

@Data
public class PostPedidosDTO {
	
	
	private Instant dataHoraCriacao;
	private String nomefantasia;
	private String cnpj;
	private String numerodopedido;
	private String venda;
	private String notafiscal;
	private String valor;
	private Integer qtd_certificados;
	private Integer qtd_creditos;
    private String comprador;
    private String telefone;
    private String email;
}
