package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PutCursoDTO {
	
	private UUID idcurso;
	private Instant dataHoraCriacao;
    private String curso;
	private String status;
	private String conteudo;
	private String modelo_certificado;
	private String campo_especifico;


}
