package br.com.jbst.services;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.jbst.DTO.GetCursoDTO;
import br.com.jbst.DTO.PostCursoDTO;
import br.com.jbst.DTO.PutCursoDTO;
import br.com.jbst.entities.Curso;
import br.com.jbst.repositories.CursoRepository;


@Service
public class CursoService {

	

    @Autowired
	CursoRepository cursoRepository;
  
    @Autowired
	ModelMapper modelMapper;
    
    public GetCursoDTO criarCurso(PostCursoDTO dto) {
    	Curso curso = modelMapper.map(dto, Curso.class);
    	curso.setIdcurso(UUID.randomUUID());
    	curso.setDataHoraCriacao(Instant.now());
    	cursoRepository.save(curso);
		return modelMapper.map(curso, GetCursoDTO.class);
		
    }
    
    public GetCursoDTO editarCurso(PutCursoDTO dto) {
		UUID id = dto.getIdcurso();
		Curso curso = cursoRepository.findById(id).orElseThrow();
		modelMapper.map(dto, curso );
		dto.setDataHoraCriacao(Instant.now());
		cursoRepository.save(curso);
		return modelMapper.map(curso, GetCursoDTO.class);
	}

    
    public List<GetCursoDTO> consultarCursos(String curso) throws Exception {
		List<Curso> cursos = cursoRepository.findAll();
		List<GetCursoDTO> lista = modelMapper.map(cursos, new TypeToken<List<GetCursoDTO>>() {
		}.getType());
		return lista;
	} 
    
    
    public GetCursoDTO consultarUmCurso(UUID idCurso) {
        Optional<Curso> curso = cursoRepository.findById(idCurso);

        if (curso.isPresent()) {
            Curso cursos = curso.get();
            return modelMapper.map(cursos, GetCursoDTO.class);
        } else {
          
			throw new RuntimeException("Curso não encontrado"); // Lançar exceção quando não encontrada
        }
    }
    

    public GetCursoDTO excluirCurso(UUID id) throws Exception  {
		Optional<Curso> curso = cursoRepository.findById(id);
		if (curso.isEmpty())
			throw new IllegalArgumentException("Instrutor não existe: " + id);
		// capturando o funcionario do banco de dados
	    Curso cursos = curso.get();
		// excluindo o funcionario no banco de dados
		cursoRepository.delete(cursos);
		GetCursoDTO dto = modelMapper.map(cursos, GetCursoDTO.class);
		dto.getCurso();
	    return dto;

	}

}

    

