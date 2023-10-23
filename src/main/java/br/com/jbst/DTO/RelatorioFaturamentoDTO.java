package br.com.jbst.DTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import lombok.Data;


@Data
public class RelatorioFaturamentoDTO {
	private UUID idfaturamento;
	private Instant dataHoraCriacao;
	private String empresa;
	private String cnpj;
    private String data_inicio;
    private String data_fim;
    private List<RelatorioMatriculaDTO> matriculas;
	private BigDecimal total;

}
