package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import br.com.jbst.DTOs.GetEnderecoDTO;
import lombok.Data;

@Data
public class GetUnidadeDeTreinamentoDTO {

	private UUID idUnidadedetreinamento;
	private Instant dataHoraCriacao;
	private String unidadedetreinamento;
	private String cnpj;
    private String unidade;
    private GetEnderecoDTO endereco;
 
}
