package br.com.jbst.DTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class RelatorioPedidosDTO {

	
	private UUID idPedidos;
	private Instant dataHoraCriacao;
	private String nomefantasia;
	private String cnpj;
	private String numerodopedido;
	private String venda;
	private String notafiscal;
	private String valor;
	private Integer creditos;
	private Integer matriculas;
    private String comprador;
    private String telefone;
    private String email;
    private List<RelatorioMatriculaDTO> matricula;
	private BigDecimal total;

}
