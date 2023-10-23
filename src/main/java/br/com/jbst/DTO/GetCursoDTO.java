package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class GetCursoDTO {
    private UUID idcurso;
	private Instant dataHoraCriacao;
	private String curso;
	private String status;	
	private String conteudo;
	private String modelo_certificado;
	private String campo_especifico;
}
