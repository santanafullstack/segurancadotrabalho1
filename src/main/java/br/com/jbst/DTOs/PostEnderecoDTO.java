package br.com.jbst.DTOs;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PostEnderecoDTO {
	
    private UUID idUnidadedetreinamento;
    private Instant dataHoraCriacao;
    private String cep;
    private String logradouro;
    private String complemento;
    private String numero;
    private String bairro;
    private String localidade;
    private String uf;

}
