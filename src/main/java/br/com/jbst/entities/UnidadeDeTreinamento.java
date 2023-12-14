package br.com.jbst.entities;

import java.time.Instant;

import java.util.List;
import java.util.UUID;

import br.com.jbst.entities.map.Contato;
import br.com.jbst.entities.map.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "unidadedetreinamento")
public class UnidadeDeTreinamento {

    @Id
    @Column(name = "idUnidadedetreinamento")
    private UUID idUnidadedetreinamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datahoracriacao", nullable = false) 
    private Instant dataHoraCriacao;

    @Column(name = "unidadedetreinamento", length = 100, nullable = false)
    private String unidadedetreinamento;

    @Column(name = "cnpj", length = 100, nullable = false)
    private String cnpj;

    @Column(name = "unidade", length = 200, nullable = false)
    private String unidade;
    
    @OneToMany(mappedBy = "unidadeDeTreinamento")
    private List<Turmas> turmas;

    @OneToOne(mappedBy =  "unidadedetreinamento")
    private Endereco endereco; 
    
    @OneToMany(mappedBy = "unidadedetreinamento") //1 Empresa tem muitos contatos
    private List<Contato> contatos;

}



