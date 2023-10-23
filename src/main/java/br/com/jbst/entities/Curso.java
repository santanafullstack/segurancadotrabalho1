package br.com.jbst.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "curso")
public class Curso {
	@Id
	@Column(name = "idcurso")
	private UUID idcurso;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	@Column(name = "curso", length = 500, nullable = false)
	private String curso;
	
	@Column(name = "status", length = 100, nullable = true)
	private String status;
	
	@Column(name = "conteudo", length = 5000, nullable = true)
	private String conteudo;
	
	@Column(name = "modelo_certificado", length = 100, nullable = true)
	private String modelo_certificado;
	
	@Column(name = "campo_especifico", length = 100, nullable = true)
	private String campo_especifico;
	
	
	@OneToMany(mappedBy = "curso") //1 Empresa tem muitos Funcionários
	private List <Turmas> turmas;
	
	
}
