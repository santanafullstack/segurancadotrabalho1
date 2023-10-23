
package br.com.jbst.entities.map;

import java.time.Instant;
import java.util.UUID;

import br.com.jbst.entities.Instrutor;
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
@Table(name = "formacao")
public class Formacao {	
		
	@Id
	@Column(name = "idformacao")
	private UUID idFormacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false) 
	private Instant dataHoraCriacao;

	@Column(name = "formacao", length = 100, nullable = false)
	private String formacao;

	@Column(name = "conselho", length = 50, nullable = true)
	private String conselho;

	@Column(name = "registro", length = 100, nullable = true)
	private String registro;

	@Column(name = "estado", length = 100, nullable = true)
	private String estado;
		
	@Column(name = "proficiencia", nullable = true)
	private byte[] proficiência;
	
	@ManyToOne 
	@JoinColumn(name = "id_instrutor", nullable = false)
	private Instrutor instrutores;
	

}
