package br.com.jbst.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.entities.map.Empresa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "faturamento")
public class Faturamento {

    @Id
    @Column(name = "idfaturamento")
    private UUID idfaturamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datahoracriacao", nullable = false)
    private Instant dataHoraCriacao;

    @Column(name = "numerofaturamento", nullable = true)
    private Integer numeroFaturamento;

    @Column(name = "data_inicio", length = 100, nullable = false)
    private Instant data_inicio;

    @Column(name = "data_fim", length = 100, nullable = false)
    private Instant data_fim;

    @Column(name = "venda", length = 50, nullable = false)
    private String venda;

    @Column(name = "notafiscal", length = 50, nullable = true)
    private String notafiscal;

    @Column(name = "valor", length = 50, nullable = false)
    private String valor;

    @Column(name = "comprador", length = 50, nullable = false)
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

    
    @Column(name = "observacoes", length = 1000, nullable = false)
    private String observacoes;

    @Column(name = "total", length = 100, nullable = true)
    private BigDecimal total;

    @OneToMany(mappedBy = "faturamento")
    private List<Matriculas> matriculas;

    @Column(name = "fatura_fechada", nullable = false)
    private boolean faturaFechada;
    
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    
    // Adicionando a lista de cobranças associadas a um faturamento
    @OneToMany(mappedBy = "faturamento")
    private List<Cobranca> cobrancas;

    public void calcularTotal() {
        BigDecimal totalValue = BigDecimal.ZERO;

        if (matriculas != null && !matriculas.isEmpty()) {
            for (Matriculas matricula : matriculas) {
                totalValue = totalValue.add(matricula.getValor());
            }
        }

        this.total = totalValue;
    }

    public boolean isFaturaAberta() {
        Instant dataAtual = Instant.now();
        return dataAtual.isAfter(this.data_inicio) && dataAtual.isBefore(this.data_fim);
    }

    public void fecharMatriculasAposDataFim() {
        Instant dataAtual = Instant.now();
        if (dataAtual.isAfter(this.data_fim) && !this.faturaFechada && this.matriculas != null && !this.matriculas.isEmpty()) {
            for (Matriculas matricula : this.matriculas) {
                matricula.setFaturaFechada(true);
            }
            this.faturaFechada = true;
        }
    }

  
}
