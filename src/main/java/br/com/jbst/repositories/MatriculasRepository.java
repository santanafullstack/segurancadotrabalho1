package br.com.jbst.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.Matriculas;

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

    List<Matriculas> findByPessoafisica_Id(UUID idPessoaFisica);
    List<Matriculas> findByFaturamento_Id(UUID idFaturamento);

}


