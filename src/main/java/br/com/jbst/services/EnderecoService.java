package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTOs.GetEnderecoDTO;
import br.com.jbst.DTOs.PostEnderecoDTO;
import br.com.jbst.DTOs.PutEnderecoDTO;
import br.com.jbst.entities.map.Endereco;
import br.com.jbst.repositories.EnderecoRepository;
import br.com.jbst.repositories.UnidadeDeTreinamentoRepository;


@Service
public class EnderecoService {
	  @Autowired
	  EnderecoRepository enderecoRepository;
	  
	  
	  @Autowired
	  UnidadeDeTreinamentoRepository unidadeRepository;
	  
	    @Autowired
		ModelMapper modelMapper;
	    
	    public GetEnderecoDTO criarEndereco(PostEnderecoDTO dto) {
	    	Endereco endereco = modelMapper.map(dto, Endereco.class);
	    	endereco.setIdEndereco(UUID.randomUUID());
	    	endereco.setDataHoraCriacao(Instant.now());
	    	enderecoRepository.save(endereco);
			return modelMapper.map(endereco, GetEnderecoDTO.class);
			
	    }
	    
	    public GetEnderecoDTO editarEndereco(PutEnderecoDTO dto) {
			UUID id = dto.getIdEndereco();
			Endereco endereco = enderecoRepository.findById(id).orElseThrow();
			modelMapper.map(dto, endereco );
			endereco.setDataHoraCriacao(Instant.now());
			enderecoRepository.save(endereco);
			return modelMapper.map(endereco, GetEnderecoDTO.class);
		}

	    
	    public List<GetEnderecoDTO> consultarEndereco(String curso) throws Exception {
			List<Endereco> endereco = enderecoRepository.findAll();
			List<GetEnderecoDTO> lista = modelMapper.map(endereco, new TypeToken<List<GetEnderecoDTO>>() {
			}.getType());
			return lista;
		} 
	    
	    
	    public GetEnderecoDTO consultarUmEndereco(UUID idEndereco) {
	        Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);

	        if (endereco.isPresent()) {
	            Endereco enderecos = endereco.get();
	            return modelMapper.map(enderecos, GetEnderecoDTO.class);
	        } else {
	          
				throw new RuntimeException("Endereço não encontrado"); // Lançar exceção quando não encontrada
	        }
	    }
	    

	    public GetEnderecoDTO excluirEndereco(UUID id) throws Exception  {
			Optional<Endereco> endereco = enderecoRepository.findById(id);
			if (endereco.isEmpty())
				throw new IllegalArgumentException("Endereço não existe: " + id);
			// capturando o funcionario do banco de dados
			Endereco enderecos = endereco.get();
			// excluindo o funcionario no banco de dados
			enderecoRepository.delete(enderecos);
			GetEnderecoDTO dto = modelMapper.map(enderecos, GetEnderecoDTO.class);
			dto.getIdEndereco();
		    return dto;

		}

	}

	    



