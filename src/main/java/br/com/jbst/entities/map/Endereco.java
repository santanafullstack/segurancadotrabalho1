package br.com.jbst.entities.map;

import java.time.Instant;
import java.util.UUID;

import br.com.jbst.entities.UnidadeDeTreinamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @Column(name = "idendereco")
    private UUID idEndereco;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datahoracriacao", nullable = false)
    private Instant dataHoraCriacao;

    @Column(name = "cep", length = 15, nullable = false)
    private String cep;

    @Column(name = "logradouro", length = 100, nullable = false)
    private String logradouro;

    @Column(name = "complemento", length = 100, nullable = false)
    private String complemento;

    @Column(name = "numero", length = 100, nullable = false)
    private String numero;

    @Column(name = "bairro", length = 100, nullable = false)
    private String bairro;

    @Column(name = "localidade", length = 100, nullable = false)
    private String localidade;

    @Column(name = "uf", length = 100, nullable = false)
    private String uf;

    @Column(name = "ibge", length = 100, nullable = true)
    private String ibge;

    @Column(name = "gia", length = 100, nullable = true)
    private String gia;

    @Column(name = "ddd", length = 5, nullable = true)
    private String ddd;

    @Column(name = "siafi", length = 100, nullable = true)
    private String siafi;
   
    @OneToOne(mappedBy =  "endereco")
    private UnidadeDeTreinamento unidadeDeTreinamento; 
    
}
