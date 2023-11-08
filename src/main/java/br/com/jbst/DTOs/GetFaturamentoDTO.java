package br.com.jbst.DTOs;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.DTO.GetMatriculaDTO;
import br.com.jbst.DTO.RelatorioMatriculaDTO;
import br.com.jbst.config.InstantSerializer;
import lombok.Data;

@Data
public class GetFaturamentoDTO {
	private UUID idfaturamento;
    private Integer numeroFaturamento;
	private String empresa;
	private String cnpj;
	@JsonSerialize(using = InstantSerializer.class)
    private Instant dataHoraCriacao;

	@JsonSerialize(using = InstantSerializer.class)
	private Instant data_inicio;
	
	@JsonSerialize(using = InstantSerializer.class)
    private Instant	 data_fim;
	
    private List<RelatorioMatriculaDTO> matriculas;
   	private BigDecimal total;
}
