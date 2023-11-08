package br.com.jbst.entities.map;
import java.time.Instant;
import java.util.UUID;
import br.com.jbst.entities.UnidadeDeTreinamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "contato")
public class Contato {
	
	@Id
	@Column(name = "idContato")
	private UUID idContato;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	@Column(name = "contato", length = 100, nullable = false)
	private String contato;
	
	@Column(name = "telefone_1", length = 100, nullable = false)
	private String telefone_1;
	
	@Column(name = "telefone_2", length = 100, nullable = true)
	private String telefone_2;
	
	@Column(name = "email", length = 100, nullable = false)
	private String email;

	@ManyToOne // muitos contatos  para 1 empresa
	@JoinColumn(name = "idempresa", nullable = true) // O JoinColumn é para mapeamento de chave estrangeira//
	private Empresa empresa;
	
	@ManyToOne // muitos contatos  para 1 empresa
	@JoinColumn(name = "idunidadedetreinamento", nullable = true) // O JoinColumn é para mapeamento de chave estrangeira//
	private UnidadeDeTreinamento unidadedetreinamento;
	
}