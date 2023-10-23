package br.com.jbst.entities;

import java.time.Instant;


import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "turmas")
public class Turmas {
	
	
	@Id
	@Column(name = "idturmas")
	private UUID idTurmas;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	 @Column(name = "numeroturma", nullable = false)
	private Integer numeroTurma;
	
	@Column(name = "datainicio", nullable = false)
	private Instant datainicio;
	
	@Column(name = "datafim", nullable = false)
	private Instant datafim;
	
	@Column(name = "cargahoraria", nullable = false)
	private String  cargahoraria;
	
	@Column(name = "modalidade", nullable = false)
	private String modalidade;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(name = "diasespecificos", nullable = false)
	private String diasespecificos;
	
	@Column(name = "tipo", nullable = false)
	private String tipo;
	
	@Column(name = "validade", nullable = false)
	private String validade;
	
	@Column(name = "dia", nullable = false)
	private String dia;
	
	@Column(name = "mes", nullable = false)
	private String mes;
	
	@Column(name = "ano", nullable = false)
	private String ano;
    
    @ManyToOne
    @JoinColumn(name = "idunidadedetreinamento", referencedColumnName = "idUnidadedetreinamento", nullable = false)
    private UnidadeDeTreinamento unidadeDeTreinamento;
	
	
	@ManyToOne // muitos contatos  para 1 empresa
	@JoinColumn(name = "idcurso", nullable = false) // O JoinColumn é para mapeamento de chave estrangeira//
	private Curso curso;
    
	@OneToMany(mappedBy = "turmas") //1 Empresa tem muitos Funcionários
	private List<Matriculas> matricula;
	

	  @ManyToMany
	    @JoinTable(
	        name = "turma_instrutor",
	        joinColumns = @JoinColumn(name = "idturmas"),
	        inverseJoinColumns = @JoinColumn(name = "idinstrutor")
	    )
	    private List<Instrutor> instrutores;
	
}
