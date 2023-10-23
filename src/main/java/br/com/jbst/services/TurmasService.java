package br.com.jbst.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetTurmasDTO;
import br.com.jbst.DTO.GetTurmasDTOs;
import br.com.jbst.DTO.PostTurmasDTO;
import br.com.jbst.DTO.PutTurmasDTO;
import br.com.jbst.entities.Curso;
import br.com.jbst.entities.Instrutor;
import br.com.jbst.entities.Turmas;
import br.com.jbst.repositories.CursoRepository;
import br.com.jbst.repositories.InstrutorRepository;
import br.com.jbst.repositories.TurmasRepository;
import br.com.jbst.repositories.UnidadeDeTreinamentoRepository;



@Service
public class TurmasService {
	
    @Autowired
    InstrutorRepository instrutorRepository;

    @Autowired
	CursoRepository cursoRepository;
    
    
    @Autowired
    TurmasRepository turmasRepository;
  
    @Autowired
	ModelMapper modelMapper;
    
    @Autowired
	UnidadeDeTreinamentoRepository unidadeRepository;
    
    
    
    public GetTurmasDTO criarTurmas(PostTurmasDTO dto) throws Exception {
		
		List<Instrutor> registro = new ArrayList<Instrutor>();
		
		//percorrendo os ids de cada instrutor enviado
		for(UUID idInstrutor : dto.getIdsInstrutor()) {			
			//consultando o Instrutor no banco de dados através do id
			Optional<Instrutor> item = instrutorRepository.findById(idInstrutor);			
			//verificando se o Instrutor não foi encontrado
			if(item.isEmpty())
				throw new IllegalArgumentException("Instrutor inválido: " + idInstrutor);			
			//adicionando a categoria na lista
			registro.add(item.get());
		}
		
		//criando uma Turma
		Turmas turmas = modelMapper.map(dto, Turmas.class);
		turmas.setIdTurmas(UUID.randomUUID());
		turmas.setInstrutores(registro);
		turmas.setCurso(cursoRepository.findById(dto.getIdCurso()).get());
		turmas.setUnidadeDeTreinamento(unidadeRepository.findById(dto.getIdUnidadeDeTreinamento()).get());
		int numeroTurma = gerarNumeroTurma();
        turmas.setNumeroTurma(numeroTurma);
		turmasRepository.save(turmas);
		return modelMapper.map(turmasRepository.find
				(turmas.getIdTurmas()), GetTurmasDTO.class);
	}
    
    private int gerarNumeroTurma() {       
        Integer ultimoNumero = turmasRepository.findMaxNumeroTurmas();
        if (ultimoNumero == null) {
            ultimoNumero = 0;
        }
        return ultimoNumero + 1;
    }
    
 public GetTurmasDTO editarTurmas(PutTurmasDTO dto) throws Exception {
		
		List<Instrutor> registro = new ArrayList<Instrutor>();
		
		//percorrendo os ids de cada instrutor enviado
		for(UUID idInstrutor : dto.getIdsInstrutor()) {			
			//consultando o Instrutor no banco de dados através do id
			Optional<Instrutor> item = instrutorRepository.findById(idInstrutor);			
			//verificando se o Instrutor não foi encontrado
			if(item.isEmpty())
				throw new IllegalArgumentException("Instrutor inválido: " + idInstrutor);			
			//adicionando a categoria na lista
			registro.add(item.get());
		}
		
		UUID id = dto.getIdTurmas();
		Turmas turmas = turmasRepository.findById(id).orElseThrow();
		modelMapper.map(dto, turmas );
		turmas.setInstrutores(registro);
		turmas.setCurso(cursoRepository.findById(dto.getIdcurso()).get());
		turmas.setUnidadeDeTreinamento(unidadeRepository.findById(dto.getIdUnidadedetreinamento()).get());

		//gravando turmas no banco de dados
		turmasRepository.save(turmas);
		
		//copiar os dados de Turmas para o DTO de resposta
		//e retornar os dados (GetTurmasDTO)
		return modelMapper.map(turmasRepository.find
				(turmas.getIdTurmas()), GetTurmasDTO.class);
	}
    
public List<GetTurmasDTOs> consultarTurmas(String descricao) throws Exception {
		
		//consultando os produtos através do nome no banco de dados
		List<Turmas> turmas = turmasRepository.findAllByDescricao(descricao);
		
		//retornando os dados dos produtos
		List<GetTurmasDTOs> lista = modelMapper.map(turmas, new TypeToken<List<GetTurmasDTOs>>() {
		}.getType());
		return lista;		
	}
	
public GetTurmasDTOs consultarTurmasPorId(UUID id) throws Exception {
	
	//consultando o produto através do ID
	Turmas turmas = turmasRepository.find(id);
	if(turmas == null)
		throw new IllegalArgumentException("Turma não encontrada: " + id);
	
	//retornando os dados
	return modelMapper.map(turmas, GetTurmasDTOs.class);		
}

public GetTurmasDTOs excluirTurmas(UUID id) throws Exception {
	
	//verificando se o produto existe (baseado no ID informado)
	Optional<Turmas> registro = turmasRepository.findById(id);
	if(registro.isEmpty())
		throw new IllegalArgumentException("Turma não existe: " + id);		
	
	//capturando o produto do banco de dados
	Turmas turmas = registro.get();
			
	//excluindo o produto no banco de dados
	turmasRepository.delete(turmas);
	
	//copiar os dados do produto para o DTO de resposta
	//e retornar os dados (ProdutosGetDTO)
	return modelMapper.map(turmas, GetTurmasDTOs.class);
}

}
