package br.com.jbst.DTO;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class GetTurmasDTO {

	private UUID idTurmas;
	private Instant dataHoraCriacao;
	private Integer numeroTurma;
	private Instant datainicio;
	private Instant datafim;
	private String  cargahoraria;
	private String modalidade;
	private String status;
	private String descricao;
	private String diasespecificos;
	private String tipo;
	private String validade;
	private String dia;
	private String mes;
	private String ano;
	private GetCursoDTO curso;
	private GetUnidadeDeTreinamentoDTO unidadeDeTreinamento;
	private List<GetInstrutorDTO> instrutores;

}
