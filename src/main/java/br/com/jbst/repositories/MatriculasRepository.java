package br.com.jbst.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jbst.entities.Matriculas;
import br.com.jbst.entities.Turmas;

public interface MatriculasRepository extends JpaRepository<Matriculas, UUID > {

	
	@Query("SELECT MAX(m.numeroMatricula) FROM Matriculas m")
    Integer findMaxNumeroMatricula();
	
	
	@Query(
			"select m from Matriculas m "
		  + "order by m.numeroMatricula"
	)
	List<Matriculas> findAllMatriculas();
	
	
	@Query(
     "SELECT m, f FROM Matriculas m\n"
      + "LEFT JOIN m.funcionario f\n"		 
	)
	List<Matriculas> findAllMatricula();
}


