package br.com.jbst.entities;


import java.math.BigDecimal;

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
@Table(name = "faturamentopf")
public class FaturamentoPf {

	@Id
	@Column(name = "idfaturamentopf")
	private UUID idfaturamentopf;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	@Column(name = "numerofaturamento", nullable = true)
	private Integer numeroFaturamento;
	
	@Column(name = "pessoafisica", length = 100, nullable = false)
	private String pessoafisica;
	
	@Column(name = "cpf", length = 100, nullable = false)
	private String cpf;
	
	@Column(name = "data_inicio", length = 100, nullable = false)
    private Instant data_inicio;
	
	@Column(name = "data_fim", length = 100, nullable = false)
    private Instant data_fim;
	
	@Column(name = "total", length = 100, nullable = true)
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
	}


