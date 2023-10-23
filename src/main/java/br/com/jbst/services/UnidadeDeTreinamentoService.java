package br.com.jbst.services;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetUnidadeDeTreinamentoDTO;
import br.com.jbst.DTO.PostUnidadeDeTreinamentoDTO;
import br.com.jbst.DTO.PutUnidadeDeTreinamentoDTO;
import br.com.jbst.entities.UnidadeDeTreinamento;
import br.com.jbst.repositories.EnderecoRepository;
import br.com.jbst.repositories.UnidadeDeTreinamentoRepository;




@Service
public class UnidadeDeTreinamentoService {

	    @Autowired
		UnidadeDeTreinamentoRepository unidadeRepository;
	  
	    @Autowired
		EnderecoRepository enderecoRepository;
	    
	    @Autowired
		ModelMapper modelMapper;
	    
	    public GetUnidadeDeTreinamentoDTO criarUnidadeDeTreinamento(PostUnidadeDeTreinamentoDTO dto) {
	    	UnidadeDeTreinamento unidade = modelMapper.map(dto, UnidadeDeTreinamento.class);
	    	unidade.setIdUnidadedetreinamento(UUID.randomUUID());
	    	unidade.setDataHoraCriacao(Instant.now());
	    	unidade.setEndereco(enderecoRepository.findById(dto.getEndereco_id()).get());
	    	unidadeRepository.save(unidade);
			return modelMapper.map(unidade, GetUnidadeDeTreinamentoDTO.class);
			
	    }
	    
	    public GetUnidadeDeTreinamentoDTO editarUnidadeDeTreinamento(PutUnidadeDeTreinamentoDTO dto) {
			UUID id = dto.getIdUnidadedetreinamento();
			UnidadeDeTreinamento unidade = unidadeRepository.findById(id).orElseThrow();
			modelMapper.map(dto, unidade);
			dto.setDataHoraCriacao(Instant.now());
	    	unidade.setEndereco(enderecoRepository.findById(dto.getEndereco_id()).get());
			unidadeRepository.save(unidade);
			return modelMapper.map(unidade, GetUnidadeDeTreinamentoDTO.class);
		}

	    
	    public List<GetUnidadeDeTreinamentoDTO> consultarUnidadeDeTreinamentos(String unidade) throws Exception {
	        List<UnidadeDeTreinamento> unidades = unidadeRepository.findAll();
	        List<GetUnidadeDeTreinamentoDTO> lista = unidades.stream().map(unidadess -> {
	            GetUnidadeDeTreinamentoDTO dto = modelMapper.map(unidadess, GetUnidadeDeTreinamentoDTO.class);
	           
	            if (unidadess.getEndereco() != null) {

	            }
				return dto;

	        }).collect(Collectors.toList());

	        return lista;

	    }

	    public GetUnidadeDeTreinamentoDTO consultarUmaUnidadeDeTreinamento(UUID idUnidadeDeTreinamento) {
	    	Optional<UnidadeDeTreinamento> unidadeOptional = unidadeRepository.findById(idUnidadeDeTreinamento);
	        if (unidadeOptional.isPresent()) {
	            UnidadeDeTreinamento unidade = unidadeOptional.get();
	            GetUnidadeDeTreinamentoDTO dto = modelMapper.map(unidade, GetUnidadeDeTreinamentoDTO.class);
	            return dto;
	        } else {
	            throw new RuntimeException("Unidade não encontrada");
	        }
	    }
	
	
	    

	    public GetUnidadeDeTreinamentoDTO excluirUnidadeDeTreinamento(UUID id) throws Exception  {
			Optional<UnidadeDeTreinamento> unidade = unidadeRepository.findById(id);
			if (unidade.isEmpty())
				throw new IllegalArgumentException("Instrutor não existe: " + id);
			// capturando o funcionario do banco de dados
			UnidadeDeTreinamento unidades = unidade.get();
			// excluindo o funcionario no banco de dados
			unidadeRepository.delete(unidades);
			GetUnidadeDeTreinamentoDTO dto = modelMapper.map(unidades, GetUnidadeDeTreinamentoDTO.class);
			dto.getUnidadedetreinamento();
		    return dto;

		}

	    }
	    
	   

	

	    


