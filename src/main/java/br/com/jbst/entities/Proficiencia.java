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
@Table(name = "proficiencia")
public class Proficiencia {

	
	
@Id
@Column(name = "idproficiencia")
private UUID idProficiencia;
	
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "datahoracriacao", nullable = false)
private Instant dataHoraCriacao;
	
@Column(name = "proficiencia", length = 500, nullable = false)
private String proficiencia;
	
@Column(name = "descricao", length = 500, nullable = true)
private String descricao;	

@Column(name = "inserir_proficiencia", nullable = true)
private byte[] inserir_proficiencia;


@ManyToOne 
@JoinColumn(name = "id_instrutor", nullable = false)
private Instrutor instrutores;

}
