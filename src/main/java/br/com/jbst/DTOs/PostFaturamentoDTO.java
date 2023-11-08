package br.com.jbst.DTOs;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PostFaturamentoDTO {
	private String empresa;
	private String cnpj;
    private Instant data_inicio;
    private Instant data_fim;
}
