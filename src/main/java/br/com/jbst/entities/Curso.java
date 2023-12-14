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
	
	@Column(name = "codigo", nullable = false)
	 private Integer codigo;
	
	@Column(name = "descricao", length = 500, nullable = true)
	private String descricao;
	
	@Column(name = "conteudo", length = 5000, nullable = true)
	private String conteudo;
	
	@Column(name = "modelo_certificado", length = 100, nullable = true)
	private String modelo_certificado;
	
	@Column(name = "campo_especifico", length = 100, nullable = true)
	private String campo_especifico;
	
	@Column(name = "tituloautorizacao", length = 200, nullable = true)
	private String tituloautorizacao;
	
	@Column(name = "itemdaautorizacao", length = 200, nullable = true)
	private String itemdaautorizacao;
	
	@Column(name = "conteudodaautorizacao", length = 5000, nullable = true)
	private String conteudodaautorizacao;
	
	@Column(name = "conformidade", length = 1000, nullable = true)
	private String conformidade;
	
	@Column(name = "avaliacao", nullable = true)
	private byte[] avaliacao;
	
	@Column(name = "gabarito", nullable = true)
	private byte[] gabarito;
	
	@Column(name = "material", nullable = true)
	private byte[] material;
	
	@OneToMany(mappedBy = "curso") 
	private List <Turmas> turmas;

	

	
}
