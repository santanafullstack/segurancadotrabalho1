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
@Table(name = "pedidos")
public class Pedidos {

	@Id
	@Column(name = "idpedidos")
	private UUID idPedidos;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	@Column(name = "nomefantasia", nullable = false)
	private String nomefantasia;
	
	@Column(name = "cnpj", nullable = false)
	private String cnpj;
	
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
	
	@Column(name = "matriculas", nullable = true)
	private Integer matriculas;
	
	@Column(name = "comprador", nullable = false)
    private String comprador;
    
	@Column(name = "telefone", nullable = false)
    private String telefone;
    
	@Column(name = "email", nullable = false)
    private String email;
	
	@Column(name = "total", length = 100, nullable = true)
	private BigDecimal total;
	
	@OneToMany(mappedBy = "pedidos") 
	private List<Matriculas> matricula;

public void calcularTotal() {
    BigDecimal totalValue = BigDecimal.ZERO;

    if (matricula != null && !matricula.isEmpty()) {
        for (Matriculas matricula : matricula) {
            totalValue = totalValue.add(matricula.getValor());
        }
    }

    this.total = totalValue;
}



}







