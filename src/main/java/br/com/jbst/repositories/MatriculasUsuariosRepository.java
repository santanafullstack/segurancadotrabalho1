package br.com.jbst.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.MatriculasUsuarios;

public interface MatriculasUsuariosRepository extends JpaRepository<MatriculasUsuarios, UUID> {

    // You don't need to declare the save method here.
    // Spring Data JPA will provide it for you.

    // You can add additional custom query methods if needed.
}
