package br.com.jbst.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.MatriculasDTO.GetPessoaFisicaDTO;
import br.com.jbst.entities.map.PessoaFisica;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "faturamentopf")
@NamedEntityGraph(name = "faturamentopf-with-pessoaFisica", attributeNodes = @NamedAttributeNode("pessoaFisica"))
public class FaturamentoPf {

    @Id
    @Column(name = "idfaturamentopf")
    private UUID idfaturamentopf;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datahoracriacao", nullable = false)
    private Instant dataHoraCriacao;

    @Column(name = "numerofaturamento", nullable = true)
    private Integer numeroFaturamento;

    @Column(name = "data_inicio", nullable = false)
    private Instant data_inicio;

    @Column(name = "data_fim", nullable = false)
    private Instant data_fim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa_fisica", nullable = false)
    private PessoaFisica pessoaFisica;

    @Column(name = "venda", length = 50, nullable = false)
    private String venda;
    
    @Column(name = "valor", length = 50, nullable = false)
    private String valor;
    
	@Column(name = "notafiscal", nullable = false)
	private String notafiscal;

    @Column(name = "observacoes", length = 1000, nullable = false)
    private String observacoes;

    @Column(name = "fatura_fechada", nullable = false)
    private boolean faturaFechada;
    
    
    @Column(name = "comprador", length = 50, nullable = true)
    private String comprador;

    @Column(name = "telefone", length = 50, nullable = false)
    private String telefone;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "responsavelfinanceiro", length = 50, nullable = false)
    private String responsavelfinanceiro;

    @Column(name = "telefonefinanceiro", length = 50, nullable = false)
    private String telefonefinanceiro;

    @Column(name = "whatsapp", length = 50, nullable = false)
    private String whatsapp;

    @Column(name = "emailfinanceiro", length = 50, nullable = false)
    private String emailfinanceiro;


    @Column(name = "data_de_pagamento", length = 100, nullable = false)
    private Instant data_de_pagamento;
	
	
    @Column(name = "parcelas", length = 50, nullable = false)
    private String parcelas;
	
	
    @Column(name = "forma_de_pagamento", length = 50, nullable = false)
    private String forma_de_pagamento;
    
    // Corrigindo o mapeamento da coleção de cobranças
    @OneToMany(mappedBy = "faturamentoPf")
    private List<Cobranca> cobrancas;


    @Column(name = "total", nullable = true)
    private BigDecimal total;

    @OneToMany(mappedBy = "faturamentopf")
    private List<Matriculas> matriculas;

   

	
}
