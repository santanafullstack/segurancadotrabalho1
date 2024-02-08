package br.com.jbst.entities;
import java.time.Instant;

import java.util.List;
import java.util.UUID;

import br.com.jbst.entities.map.Empresa;
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
	

	@Column(name = "numeroturma", nullable = true)
	private Integer numeroTurma;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datainicio", nullable = true)
	private Instant datainicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datafim", nullable = true)
	private Instant datafim;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "validadedocurso", nullable = true)
	private Instant validadedocurso;
	
	@Column(name = "cargahoraria", nullable = true)
	private String  cargahoraria;
	
	@Column(name = "modalidade", nullable = true)
	private String modalidade;
	
	@Column(name = "status", nullable = true)
	private String status;
	
	@Column(name = "descricao", nullable = true)
	private String descricao;
	
	@Column(name = "diasespecificos", nullable = true)
	private String diasespecificos;
	
	@Column(name = "tipo", nullable = true)
	private String tipo;
	
	@Column(name = "nivel", nullable = true)
	private String nivel;
	
	@Column(name = "validade", nullable = true)
	private String validade;
	
	@Column(name = "dia", nullable = true)
	private String dia;
	
	@Column(name = "mes", nullable = true)
	private String mes;
	
	@Column(name = "ano", nullable = true)
	private String ano;
	
	@Column(name = "primeirodia", nullable = true)
	private String primeirodia;

	@Column(name = "segundodia", nullable = true)
	private String segundodia;

	@Column(name = "terceirodia", nullable = true)
	private String terceirodia;

	@Column(name = "quartodia", nullable = true)
	private String quartodia;

	@Column(name = "quintodia", nullable = true)
	private String quintodia;
    
	@Column(name = "turma_fechada", nullable = false)
	private boolean turmaFechada;
	
	@Column(name = "matriculas_bloqueadas", nullable = false)
	private boolean matriculasBloqueadas;
	
    @ManyToOne
    @JoinColumn(name = "idunidadedetreinamento", referencedColumnName = "idUnidadedetreinamento", nullable = true)
    private UnidadeDeTreinamento unidadeDeTreinamento;
	
	
	@ManyToOne // muitos contatos  para 1 empresa
	@JoinColumn(name = "idcurso", nullable = true) // O JoinColumn é para mapeamento de chave estrangeira//
	private Curso curso;
    
	@OneToMany(mappedBy = "turmas") //1 Empresa tem muitos Funcionários
	private List<Matriculas> matricula;
	

	  @ManyToMany
	    @JoinTable(
	        name = "turma_instrutor",
	        joinColumns = @JoinColumn(name = "idturmas"),
	        inverseJoinColumns = @JoinColumn(name = "idinstrutor", nullable = true )
	    )
	    private List<Instrutor> instrutores;


	public Empresa getEmpresa() {
		return null;
	}
	
	
	public void turmaAberta() {
        if (this.turmaFechada) {
            this.turmaFechada = false;
            this.matriculasBloqueadas = false;
        }
    }

    public void turmaFechada() {
        if (!this.turmaFechada) {
            this.turmaFechada = true;
            this.matriculasBloqueadas = true;
        }
    }
	
}
