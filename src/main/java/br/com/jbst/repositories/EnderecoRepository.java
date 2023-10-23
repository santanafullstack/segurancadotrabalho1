package br.com.jbst.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.map.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

}
