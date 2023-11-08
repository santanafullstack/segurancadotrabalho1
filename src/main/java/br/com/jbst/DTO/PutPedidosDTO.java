package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PutPedidosDTO {
	
	private UUID idPedidos;
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
