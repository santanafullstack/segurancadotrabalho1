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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "pedidos")
public class Pedidos {

	@Id
	@Column(name = "idpedidos")
	private UUID idPedidos;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;

	@Column(name = "numerodopedido", nullable = false)
	private String numerodopedido;

	@Column(name = "venda", nullable = false)
	private String venda;

	@Column(name = "notafiscal", nullable = false)
	private String notafiscal;

	@Column(name = "valor", nullable = false)
	private String valor;

	@Column(name = "creditos", nullable = false)
	private Integer creditos;

	@Column(name = "matriculasrealizadas", nullable = true)
	private Integer matriculasrealizadas;

	@Column(name = "comprador", nullable = false)
	private String comprador;

	@Column(name = "telefone", nullable = false)
	private String telefone;

	@Column(name = "email", nullable = false)
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

	@Column(name = "pedido_fechado", nullable = false)
	private boolean pedidoFechado;

	@Column(name = "matriculas_bloqueadas", nullable = false)
	private boolean matriculasBloqueadas;

	@OneToMany(mappedBy = "pedidos")
	private List<Matriculas> matricula;
	
	
    @OneToMany(mappedBy = "pedidos")
    private List<Cobranca> cobrancas;

	@ManyToOne
	@JoinColumn(name = "idempresa", nullable = false) // Specify the join column name
	private Empresa empresa;

	public void calcularTotal() {
		BigDecimal totalValue = BigDecimal.ZERO;

		if (matricula != null && !matricula.isEmpty()) {
			for (Matriculas matricula : matricula) {
				totalValue = totalValue.add(matricula.getValor());
			}
		}

		this.total = totalValue;
	}



	public boolean fecharPedido() {
	    if (!this.pedidoFechado) {
	        // Coloque aqui as ações necessárias ao fechar o pedido, se houver
	        this.pedidoFechado = true;
	        this.matriculasBloqueadas = true;
	    }
		return matriculasBloqueadas;
	}

	public void abrirPedido() {
	    if (this.pedidoFechado) {
	        // Coloque aqui as ações necessárias ao abrir o pedido, se houver
	        this.pedidoFechado = false;
	        this.matriculasBloqueadas = false;
	    }
	}

	    }
