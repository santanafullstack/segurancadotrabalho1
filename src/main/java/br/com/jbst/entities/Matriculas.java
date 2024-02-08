package br.com.jbst.entities;

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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

    @Column(name = "numeromatricula", nullable = true)
    private Integer numeroMatricula;

    @Column(name = "status", length = 100, nullable = false)
    private String status;

    @Column(name = "tipo_de_pagamento", length = 100, nullable = false)
    private String tipo_de_pagamento;
    
    @Column(name = "observacoes", length = 1000, nullable = true)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_turmas", nullable = true)
    private Turmas turmas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idFuncionario", nullable = true)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_pessoa_fisica", nullable = true)
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

    @ManyToMany
    @JoinTable(
        name = "usuarios_matricula",
        joinColumns = @JoinColumn(name = "id_matricula"),
        inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private List<Usuario> usuarios;

    @Column(name = "fatura_fechada", nullable = false)
    private boolean faturaFechada;

	@Column(name = "pedido_fechado", nullable = false)
	private boolean pedidoFechado;
    
	
	public void bloquearMatriculas() {
	    // Verifica se a turma associada está fechada
	    if (this.getTurmas().getStatus().equals("Fechada")) {
	        throw new IllegalStateException("Não é possível adicionar matrículas a uma turma fechada.");
	    }

	}

}
