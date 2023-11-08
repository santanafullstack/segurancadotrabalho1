package br.com.jbst.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.FaturamentoPf;

public interface FaturamentopfRepository extends JpaRepository< FaturamentoPf,UUID> {

	
	@Query("SELECT MAX(f.numeroFaturamento) FROM FaturamentoPf f")
    Integer findMaxNumeroFaturamento();

}
