package br.com.jbst.DTO;

import java.time.Instant;

import java.util.List;
import java.util.UUID;


import lombok.Data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import br.com.jbst.config.InstantSerializer;

@Data
public class GetTurmasDTOs {

	private UUID idTurmas;
	
	@JsonSerialize(using = InstantSerializer.class)
	private Instant dataHoraCriacao;
    
	private Integer numeroTurma;
	
	@JsonSerialize(using = InstantSerializer.class)
	private Instant datainicio;
    
	@JsonSerialize(using = InstantSerializer.class)
	private Instant datafim;
	
	private String  cargahoraria;
	private String modalidade;
	private String status;
	private String descricao;
	private String diasespecificos;
	private String tipo;
	private String nivel;
	private String validade;
	private String dia;
	private String mes;
	private String ano;
	private String primeirodia;
	private String segundodia;
	private String terceirodia;
	private String quartodia;
	private String quintodia;
	private GetCursoDTO curso;
	
	private GetUnidadeDeTreinamentoDTO unidadeDeTreinamento;
	private List<GetInstrutorDTO> instrutores;
	private List<GetMatriculaTurmDTO> matricula;



}
