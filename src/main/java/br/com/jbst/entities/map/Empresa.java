package br.com.jbst.entities.map;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.entities.Faturamento;
import br.com.jbst.entities.Pedidos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "empresa")
public class Empresa {
	
@Id
@Column(name = "idempresa")
private UUID idEmpresa;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "datahoracriacao", nullable = false) 
private Instant dataHoraCriacao;

@Column(name = "razaosocial", length = 100, nullable = false)
private String razaosocial;

@Column(name = "nomefantasia", length = 20, nullable = false)
private String nomefantasia;

@Column(name = "cnpj", length = 100, nullable = false)
private String cnpj;

@Column(name = "status", length = 100, nullable = false)
private String status;

@Column(name = "inscricaoestadual", length = 100, nullable = true)
private String inscricaoestadual;

@Column(name = "inscricaomunicipal", length = 100, nullable = true)
private String inscricaomunicipal;

	
@Column(name = "assinatura")
private byte[] logo;


@OneToMany(mappedBy = "empresa") //1 Empresa tem muitos Funcionários
private List<Funcionario> funcionarios;

@OneToMany(mappedBy = "empresa")
private List<Faturamento> faturamentos;

@OneToMany(mappedBy = "empresa") // 1 Empresa tem muitos Pedidos
private List<Pedidos> pedidos;

public Object getId() {
	// TODO Auto-generated method stub
	return null;
}




}
