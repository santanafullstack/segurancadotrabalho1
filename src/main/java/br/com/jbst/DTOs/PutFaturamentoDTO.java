package br.com.jbst.DTOs;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PutFaturamentoDTO {
	private UUID idfaturamento;
	private Instant dataHoraCriacao;
	private String empresa;
	private String cnpj;
    private String data_inicio;
    private String data_fim;
}
