package br.com.jbst.entities.map;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import br.com.jbst.entities.Matriculas;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionario")
public class Funcionario {
	
	@Id
	@Column(name = "idFuncionario")
	private UUID idFuncionario;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "cpf", length = 100, nullable = true)
	private String cpf;
	
	@Column(name = "rg", length = 100, nullable = false)
	private String rg;
	
	@Column(name = "status", length = 100, nullable = true)
	private String status;
	
	@Column(name = "matricula", length = 100, nullable = true)
	private String matricula;
	
	@Column(name = "assinatura", nullable = true)
	private byte[] assinatura;

	@OneToMany(mappedBy = "funcionario") 
	private List<Matriculas> matriculas;
	
	@ManyToOne // muitos funcionários para 1 empresa
	@JoinColumn(name = "empresa_id", nullable = false) // O JoinColumn é para mapeamento de chave estrangeira//
	private Empresa empresa;

	@ManyToOne // muitos funcionários para 1 função
	@JoinColumn(name = "funcao_id", nullable = false) 
	private Funcao funcao;
	

	
}
