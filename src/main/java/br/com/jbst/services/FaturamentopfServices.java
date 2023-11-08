package br.com.jbst.services;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.RelatorioFaturamentoDTO;
import br.com.jbst.DTO.RelatorioFaturamentoPfDto;
import br.com.jbst.DTOs.GetFaturamentoDTO;
import br.com.jbst.DTOs.GetFaturamentopfDto;
import br.com.jbst.DTOs.PostFaturamentopfDto;
import br.com.jbst.DTOs.PutFaturamentoDTO;
import br.com.jbst.DTOs.PutFaturamentopfDto;
import br.com.jbst.entities.Faturamento;
import br.com.jbst.entities.FaturamentoPf;
import br.com.jbst.entities.Matriculas;
import br.com.jbst.repositories.FaturamentopfRepository;


@Service
public class FaturamentopfServices {
	
	
	@Autowired
	FaturamentopfRepository faturamentopfRepository;
  
    @Autowired
	ModelMapper modelMapper;
    
    
    
    public GetFaturamentopfDto criarFaturamentopf(PostFaturamentopfDto dto) {
    	FaturamentoPf faturamentopf = modelMapper.map(dto, FaturamentoPf.class);
    	faturamentopf.setIdfaturamentopf(UUID.randomUUID());
    	faturamentopf.setDataHoraCriacao(Instant.now());
    	 int numeroFaturamento = gerarNumeroFaturamento();
         faturamentopf.setNumeroFaturamento(numeroFaturamento);
         
    	faturamentopfRepository.save(faturamentopf);
		return modelMapper.map(faturamentopf, GetFaturamentopfDto.class);
    }
		  private int gerarNumeroFaturamento() {       
	 	        Integer ultimoNumero = faturamentopfRepository.findMaxNumeroFaturamento();
	 	        if (ultimoNumero == null) {
	 	            ultimoNumero = 0;
	 	        }
	 	        return ultimoNumero + 1;
	 	    }
    
	public GetFaturamentopfDto editarFaturamentopf(PutFaturamentopfDto dto) {
	UUID id = dto.getIdfaturamentopf();
	FaturamentoPf faturamentopf = faturamentopfRepository.findById(id).orElseThrow();
	modelMapper.map(dto, faturamentopf );
	faturamentopf.setDataHoraCriacao(Instant.now());
	faturamentopfRepository.save(faturamentopf);
	return modelMapper.map(faturamentopf, GetFaturamentopfDto.class);
			}
    public List<GetFaturamentopfDto> consultarFaturamentospf(String data_inicio) throws Exception {
		List<FaturamentoPf> faturamentopf = faturamentopfRepository.findAll();
		List<GetFaturamentopfDto> lista = modelMapper.map(faturamentopf, new TypeToken<List<GetFaturamentopfDto>>() {
		}.getType());
		return lista;
	} 
    public GetFaturamentopfDto consultarUmFaturamentopf(UUID idFaturamentopf) {
        Optional<FaturamentoPf> faturamentopf = faturamentopfRepository.findById(idFaturamentopf);

        if (faturamentopf.isPresent()) {
        	FaturamentoPf faturamentos = faturamentopf.get();
            return modelMapper.map(faturamentos, GetFaturamentopfDto.class);
        } else {
          
			throw new RuntimeException("Faturamento não encontrado"); // Lançar exceção quando não encontrada
        }
    }
    
    public GetFaturamentopfDto excluirFaturamentopf(UUID id) throws Exception  {
		Optional<FaturamentoPf> faturamentopf = faturamentopfRepository.findById(id);
		if (faturamentopf.isEmpty())
			throw new IllegalArgumentException("Faturamento não existe: " + id);
		// capturando o funcionario do banco de dados
	    FaturamentoPf faturamentos = faturamentopf.get();
		// excluindo o funcionario no banco de dados
		faturamentopfRepository.delete(faturamentos);
		GetFaturamentopfDto dto = modelMapper.map(faturamentos, GetFaturamentopfDto.class);
		dto.getIdfaturamentopf();
	    return dto;

	}

    public  RelatorioFaturamentoPfDto calcularEAtualizarTotal(UUID idFaturamento) {
        Optional<FaturamentoPf> faturamentoOptional = faturamentopfRepository.findById(idFaturamento);

        if (faturamentoOptional.isPresent()) {
            FaturamentoPf faturamentopf = faturamentoOptional.get();
            BigDecimal totalValue = BigDecimal.ZERO;

            if (faturamentopf.getMatriculas() != null && !faturamentopf.getMatriculas().isEmpty()) {
                for (Matriculas matricula : faturamentopf.getMatriculas()) {
                    if (matricula.getValor() != null) {
                        totalValue = totalValue.add(matricula.getValor());
                    }
                }
            }

            faturamentopf.setTotal(totalValue);
            faturamentopfRepository.save(faturamentopf);

            return modelMapper.map(faturamentopf, RelatorioFaturamentoPfDto.class);
        } else {
            throw new RuntimeException("Faturamento não encontrado");
        }
    }

}


