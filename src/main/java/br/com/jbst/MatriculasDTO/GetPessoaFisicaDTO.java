package br.com.jbst.MatriculasDTO;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class GetPessoaFisicaDTO {
private UUID idpessoafisica;
	    private Instant dataHoraCriacao;
		private String pessoafisica;
		private String rg;
	    private String cpf;
		private String telefone_1;
		private String telefone_2;
		private String email;

}
