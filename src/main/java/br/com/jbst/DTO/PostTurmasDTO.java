package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;


import lombok.Data;

@Data
public class PostTurmasDTO {
	
	private UUID idCurso;
	private UUID idUnidadeDeTreinamento;
	private Instant datainicio;	
	private Instant datafim;	
	private Instant validadedocurso;
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

}
