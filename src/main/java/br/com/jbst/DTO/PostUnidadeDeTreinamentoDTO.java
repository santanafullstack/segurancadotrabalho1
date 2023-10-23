package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PostUnidadeDeTreinamentoDTO {


        private UUID endereco_id;
	    private Instant dataHoraCriacao;
	    private String unidadedetreinamento;
	    private String cnpj;
	    private String unidade;


}
