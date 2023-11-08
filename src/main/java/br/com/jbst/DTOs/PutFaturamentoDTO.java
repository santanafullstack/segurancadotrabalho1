package br.com.jbst.DTOs;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PutFaturamentoDTO {
	private UUID idfaturamento;
	private String empresa;
	private String cnpj;
    private Instant data_inicio;
    private Instant data_fim;
}
