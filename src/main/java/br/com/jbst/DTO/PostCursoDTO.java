package br.com.jbst.DTO;

import java.time.Instant;

import lombok.Data;

@Data
public class PostCursoDTO {
	
	private Instant dataHoraCriacao;
	private String curso;
	private String tipo;
	private String descricao;
	private String conteudo;
	private String modelo_certificado;
	private String campo_especifico;
	private String status;


}
