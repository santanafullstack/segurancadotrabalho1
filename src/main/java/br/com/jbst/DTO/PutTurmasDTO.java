package br.com.jbst.DTO;

import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

import org.modelmapper.internal.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver.Unique;

import br.com.jbst.entities.Curso;
import br.com.jbst.entities.Instrutor;
import lombok.Data;

@Data
public class PutTurmasDTO {
	
	private UUID idcurso;
	private UUID idUnidadedetreinamento;
	private UUID idTurmas;
	private Date datainicio;
	private Date datafim;
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
