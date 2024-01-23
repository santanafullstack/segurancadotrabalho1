package br.com.jbst.DTOs;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PostFaturamentoDTO {
    private Instant data_inicio;
    private Instant data_fim;
    private String venda;
    private String notafiscal;
    private String valor;
    private String comprador;
    private String telefone;
    private String email;
    private String responsavelfinanceiro;
    private String telefonefinanceiro;
    private String whatsapp;
    private String emailfinanceiro;
    private String observacoes;
    private UUID idEmpresa;  // Adicionando o ID da empresa
	public UUID getIdEmpresa() {
		// TODO Auto-generated method stub
		return null;
	}

}

