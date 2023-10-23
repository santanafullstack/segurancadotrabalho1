package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;




@Data
@Entity
@Table(name = "evidencias")
public class Evidencias {
	
@Id
@Column(name = "idevidencias")
private UUID idEvidencias;
	
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "datahoracriacao", nullable = false)
private Instant dataHoraCriacao;
	
@Column(name = "evidencias", length = 500, nullable = false)
private String evidencias;
	
@Column(name = "descricao", length = 500, nullable = true)
private String descricao;	

@Column(name = "inserir_evidencias", nullable = true)
private byte[] inserir_evidencias;


@ManyToOne 
@JoinColumn(name = "idmatriculas", nullable = false)
private Matriculas matriculas;

}