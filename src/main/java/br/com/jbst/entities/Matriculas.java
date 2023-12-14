package br.com.jbst.entities;
import java.util.ArrayList;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.entities.map.Funcionario;
import br.com.jbst.entities.map.PessoaFisica;
import jakarta.persistence.CascadeType;
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
	
	
	@Column(name = "venda", length = 100, nullable = true)
	private String venda;
	
	@Column(name = "valor", length = 100, nullable = true)
	private BigDecimal valor;
	
    @Column(name = "numeromatricula", nullable = false)
    private Integer numeroMatricula;
	
	@Column(name = "status", length = 100, nullable = false)
	private String status;
	
	@Column(name = "tipo_de_pagamento", length = 100, nullable = false)
	private String tipo_de_pagamento;
	
	@ManyToOne 
	@JoinColumn(name = "id_turmas", nullable = true) 
	private Turmas turmas;
	
	@ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "idFuncionario", nullable = true) 
	private Funcionario funcionario;
	
	@ManyToOne 
	@JoinColumn(name = "idPessoaFisica", nullable = true) 
	private PessoaFisica pessoafisica;
	
	@ManyToOne
	@JoinColumn(name = "idfaturamento", nullable = true)
	private Faturamento faturamento;
	
	@ManyToOne 
	@JoinColumn(name = "idfaturamentopf", nullable = true)
	private FaturamentoPf faturamentopf;
	
	
	@ManyToOne 
	@JoinColumn(name = "idpedidos", nullable = true) 
	private Pedidos pedidos;

	 @OneToMany(mappedBy = "matriculas") 
	private List<Evidencias> evidencias;
	 

	  
	 @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<MatriculasUsuarios> matriculasUsuarios = new ArrayList<>();
}


