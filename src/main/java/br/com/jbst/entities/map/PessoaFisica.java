package br.com.jbst.entities.map;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.entities.Matriculas;
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
@Table(name = "pessoafisica")
public class PessoaFisica {
	
	@Id
	@Column(name = "idpessoafisica")
	private UUID idpessoafisica;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	@Column(name = "pessoafisica", length = 100, nullable = false)
	private String pessoafisica;
	
	@Column(name = "rg", length = 100, nullable = true)
	private String rg;
	
	@Column(name = "cpf", length = 100, nullable = true)

	private String cpf;
	
	@Column(name = "telefone_1", length = 100, nullable = false)
	private String telefone_1;
	
	@Column(name = "telefone_2", length = 100, nullable = true)
	private String telefone_2;
	
	@Column(name = "email", length = 100, nullable = true)
	private String email;

	@Column(name = "assinatura_pessoafisica", nullable = true)
	private byte[] assinatura_pessoafisica;
	
	@OneToMany(mappedBy = "pessoafisica") 
	private List<Matriculas> matriculas;
	

}