package br.com.jbst.repositories;

import java.util.UUID;
import br.com.jbst.entities.Faturamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FaturamentoRepository extends JpaRepository<Faturamento, UUID > {

	
	
	@Query("SELECT MAX(f.numeroFaturamento) FROM Faturamento f")
    Integer findMaxNumeroFaturamento();
}
