package br.com.jbst.DTOs;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.DTO.RelatorioMatriculaDTO;
import br.com.jbst.config.InstantSerializer;
import lombok.Data;

@Data
public class GetFaturamentopfDto {
	private UUID idfaturamentopf;
	private String pessoafisica;
	private String cpf;
    private Integer numeroFaturamento;
	@JsonSerialize(using = InstantSerializer.class)
    private Instant dataHoraCriacao;
	@JsonSerialize(using = InstantSerializer.class)
    private Instant data_inicio;
	@JsonSerialize(using = InstantSerializer.class)
    private Instant data_fim;
	private List<RelatorioMatriculaDTO> matriculas;
	private BigDecimal total;
}
