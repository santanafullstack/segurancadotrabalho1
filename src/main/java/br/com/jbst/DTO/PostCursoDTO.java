package br.com.jbst.DTO;

import java.time.Instant;

import lombok.Data;

@Data
public class PostCursoDTO {
	
	private Instant dataHoraCriacao;
	private String curso;
	private String status;
	private String descricao;
	private String conteudo;
	private String modelo_certificado;
	private String campo_especifico;
	private String tituloautorizacao;
	private String itemdaautorizacao;
	private String conteudodaautorizacao;
	private String conformidade;

}
