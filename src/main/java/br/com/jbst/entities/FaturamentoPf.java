package br.com.jbst.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

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

    @Column(name = "observacoes", length = 1000, nullable = false)
    private String observacoes;

    @Column(name = "fatura_fechada", nullable = false)
    private boolean faturaFechada;

    @Column(name = "total", nullable = true)
    private BigDecimal total;

    @OneToMany(mappedBy = "faturamentopf")
    private List<Matriculas> matriculas;

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
