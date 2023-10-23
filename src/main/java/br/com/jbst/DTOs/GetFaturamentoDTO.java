package br.com.jbst.DTOs;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO.GetMatriculaDTO;
import br.com.jbst.DTO.RelatorioMatriculaDTO;
import lombok.Data;

@Data
public class GetFaturamentoDTO {
	private UUID idfaturamento;
	private Instant dataHoraCriacao;
    private Integer numeroFaturamento;
	private String empresa;
	private String cnpj;
    private String data_inicio;
    private String data_fim;
    private List<RelatorioMatriculaDTO> matriculas;
   	private BigDecimal total;
}
