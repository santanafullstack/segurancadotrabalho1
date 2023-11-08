package br.com.jbst.DTOs;

import java.time.Instant;

import lombok.Data;

@Data
public class PostFaturamentopfDto {
	private String pessoafisica;
	private String cpf;
    private Instant data_inicio;
    private Instant data_fim;
}
