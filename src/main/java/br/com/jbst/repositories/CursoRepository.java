package br.com.jbst.repositories;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, UUID > {
	
	
	@Query("SELECT MAX(c.codigo) FROM Curso c")
    Integer findMaxNumeroCurso();
	
	 @Query("SELECT COUNT(c) > 0 FROM Curso c WHERE c.curso = :nomeCurso")
	    boolean existsByCurso(String nomeCurso);
	
}
