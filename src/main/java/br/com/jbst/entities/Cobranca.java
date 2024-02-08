package br.com.jbst.entities;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
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
@Table(name = "cobranca")
public class Cobranca {

    @Id
    @Column(name = "idCobranca")
    private UUID idCobranca;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datahoracriacao", length = 100, nullable = false)
    private Instant dataHoraCriacao;


    @Column(name = "responsavelCobranca", length = 100, nullable = false)
    private String responsavelCobranca;


    @Column(name = "responsavelCliente", length = 100, nullable = false)
    private String responsavelCliente;
 

    @Column(name = "data_de_agendamento_pagamento", length = 100, nullable = false)
    private Instant data_de_agendamento_pagamento;
	

    @Column(name = "Assunto", length = 100, nullable = false)
    private String Assunto;
	
	@Column(name = "Observacoes", length = 1000, nullable = false)
    private String Observacoes;


    @ManyToOne
    @JoinColumn(name = "idfaturamento" )
    private Faturamento faturamento;


	// Exemplo na classe Cobranca
	@ManyToOne
	@JoinColumn(name = "idfaturamentopf")
	private FaturamentoPf faturamentoPf;

	@ManyToOne
    @JoinColumn(name = "idpedidos")
    private Pedidos pedidos;

  
}
