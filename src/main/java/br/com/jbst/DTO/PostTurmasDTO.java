package br.com.jbst.DTO;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.config.InstantSerializer;
import br.com.jbst.entities.Curso;
import br.com.jbst.entities.Instrutor;
import br.com.jbst.entities.UnidadeDeTreinamento;
import lombok.Data;

@Data
public class PostTurmasDTO {
	
	private UUID idCurso;
	private UUID idUnidadeDeTreinamento;
	private Instant datainicio;	
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

}
