package br.com.jbst.DTO;


import lombok.Data;

@Data
public class PostPedidosDTO {
	
	
	private String nomefantasia;
	private String cnpj;
	private String numerodopedido;
	private String venda;
	private String notafiscal;
	private String valor;
	private Integer creditos;
    private String comprador;
    private String telefone;
    private String email;
}
