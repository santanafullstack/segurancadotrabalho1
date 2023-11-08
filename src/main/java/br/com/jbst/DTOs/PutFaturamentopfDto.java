package br.com.jbst.DTOs;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PutFaturamentopfDto {
	
	private UUID idfaturamentopf;
	private String pessoafisica;
	private String cpf;
    private Instant data_inicio;
    private Instant data_fim;
}
