package br.com.jbst.DTO;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.entities.map.Formacao;
import lombok.Data;

@Data
public class PutInstrutorDTO {

	private UUID idinstrutor;
	private String instrutor;
	private String rg;
	private String cpf;
	private String telefone_1;
	private String telefone_2;
	private String email;

}
