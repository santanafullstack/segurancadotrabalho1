package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class Certificado {

	private UUID idCertificado;
	private Instant datahoracriacao;
	private String Certificado;
}
