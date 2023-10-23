package br.com.jbst.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.entities.map.Funcionario;
import br.com.jbst.entities.map.PessoaFisica;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;



@Data
@Entity
@Table(name = "matriculas")
public class Matriculas {

	@Id
	@Column(name = "idmatricula")
	private UUID idMatricula;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
    @Column(name = "numeromatricula", nullable = true)
    private Integer numeroMatriculas;
	
	
	@Column(name = "venda", length = 100, nullable = true)
	private String vendas;
	
	@Column(name = "valor", length = 100, nullable = true)
	private BigDecimal valor;
	
    @Column(name = "numeromatricula", nullable = false)
    private Integer numeroMatricula;
	
	
	@Column(name = "venda", length = 100, nullable = false)
	private String venda;
	
	@Column(name = "status", length = 100, nullable = false)
	private String status;
	
	@ManyToOne // muitos funcionários para 1 função
	@JoinColumn(name = "id_turmas", nullable = true) 
	private Turmas turmas;
	
	@ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "idFuncionario", nullable = true) 
	private Funcionario funcionario;
	
	@ManyToOne 
	@JoinColumn(name = "idPessoaFisica", nullable = true) 
	private PessoaFisica pessoafisica;
	
	@ManyToOne // muitos funcionários para 1 função
	@JoinColumn(name = "idfaturamento", nullable = true)
	private Faturamento faturamento;
	
	@ManyToOne // muitos funcionários para 1 função
	@JoinColumn(name = "idpedidos", nullable = true) 
	private Pedidos pedidos;

	 @OneToMany(mappedBy = "matriculas") //1 pessoa fisica tem uma lista de formaçoes
	private List<Evidencias> evidencias;
	 

		    
		}

