package br.com.jbst.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, UUID >{

	

	
	
}
