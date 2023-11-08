package br.com.jbst.services;
import java.time.Instant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetInstrutorDTO;
import br.com.jbst.DTO.GetInstrutorDTOs;
import br.com.jbst.DTO.GetUnidadeDeTreinamentoDTO;
import br.com.jbst.DTO.PostInstrutorDTO;
import br.com.jbst.DTO.PutInstrutorDTO;
import br.com.jbst.entities.Instrutor;
import br.com.jbst.entities.UnidadeDeTreinamento;
import br.com.jbst.entities.map.Formacao;
import br.com.jbst.repositories.FormacaoRepository;
import br.com.jbst.repositories.InstrutorRepository;


@Service
public class InstrutorService {

    @Autowired
    InstrutorRepository instrutorRepository;
 
    @Autowired
    FormacaoRepository formacaoRepository;
    
    @Autowired
    ModelMapper modelMapper;
  
    public GetInstrutorDTO criarInstrutor(PostInstrutorDTO dto) throws Exception {
        
    	Instrutor instrutor = modelMapper.map(dto, Instrutor.class);
        instrutor.setIdinstrutor(UUID.randomUUID());
        instrutor.setDataHoraCriacao(Instant.now());
        instrutorRepository.save(instrutor);
        return modelMapper.map(instrutor, GetInstrutorDTO.class);
    }
    
    public GetInstrutorDTO editarInstrutor(PutInstrutorDTO dto) throws Exception {
    	UUID id = dto.getIdinstrutor();
		Instrutor registro = instrutorRepository.findById(id).orElseThrow();
		modelMapper.map(dto, registro);
        registro.setDataHoraCriacao(Instant.now());

	    instrutorRepository.save(registro);
		return modelMapper.map(registro, GetInstrutorDTO.class);
	}
    
    
    public List<GetInstrutorDTO> consultarInstrutores(String instrutor) throws Exception {
	List<Instrutor> instrutores = instrutorRepository.findAll();
	List<GetInstrutorDTO> lista = modelMapper.map(instrutores, new TypeToken<List<GetInstrutorDTO>>() {
	}.getType());
	return lista;		
    }
    
public GetInstrutorDTO consultarInstrutorPorId(UUID id) throws Exception {
		
		//consultando o produto através do ID
	Instrutor instrutor = instrutorRepository.find(id);
		if(instrutor == null)
			throw new IllegalArgumentException("Instrutor não encontrado: " + id);
		
		//retornando os dados
		return modelMapper.map(instrutor, GetInstrutorDTO.class);		
	}

public GetInstrutorDTOs excluirInstrutor(UUID id) throws Exception {
		Optional<Instrutor> registro = instrutorRepository.findById(id);
	    if(registro.isEmpty())
		throw new IllegalArgumentException("Produto inválido: " + id);		
		Instrutor instrutor = registro.get();
		instrutorRepository.delete(instrutor);
	    return modelMapper.map(instrutor, GetInstrutorDTOs.class);
     }  

public GetInstrutorDTOs incluirAssinatura(UUID id, byte[] assinatura) throws Exception {

	// verificando se o funcionario existe (baseado no ID informado)
	Optional<Instrutor> registro = instrutorRepository.findById(id);
	if (registro.isEmpty())
		throw new IllegalArgumentException("Instrutor não Existe inválido: " + id);

	// capturando o funcionario do banco de dados
	Instrutor instrutor = registro.get();

	// alterando a foto
	instrutor.setAssinatura_instrutor(assinatura);

	// salvando no banco de dados
	instrutorRepository.save(instrutor);

	// copiar os dados do Instrutor para o DTO de resposta
	// e retornar os dados (GetInstrutorDTOs)
	return modelMapper.map(instrutor, GetInstrutorDTOs.class);
}
}




