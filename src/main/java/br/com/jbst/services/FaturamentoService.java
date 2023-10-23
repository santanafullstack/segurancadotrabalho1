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
import br.com.jbst.DTO.RelatorioPedidosDTO;
import br.com.jbst.DTOs.GetFaturamentoDTO;
import br.com.jbst.DTOs.PostFaturamentoDTO;
import br.com.jbst.DTOs.PutFaturamentoDTO;
import br.com.jbst.entities.Faturamento;
import br.com.jbst.entities.Matriculas;
import br.com.jbst.entities.Pedidos;
import br.com.jbst.repositories.FaturamentoRepository;




@Service
public class FaturamentoService {


    @Autowired
	FaturamentoRepository faturamentoRepository;
  
    @Autowired
	ModelMapper modelMapper;
    
    public GetFaturamentoDTO criarFaturamento(PostFaturamentoDTO dto) {
    	Faturamento faturamento = modelMapper.map(dto, Faturamento.class);
    	faturamento.setIdfaturamento(UUID.randomUUID());
    	faturamento.setDataHoraCriacao(Instant.now());
    	 int numeroFaturamento = gerarNumeroFaturamento();
         faturamento.setNumeroFaturamento(numeroFaturamento);
         
    	faturamentoRepository.save(faturamento);
		return modelMapper.map(faturamento, GetFaturamentoDTO.class);
    }
		  private int gerarNumeroFaturamento() {       
	 	        Integer ultimoNumero = faturamentoRepository.findMaxNumeroFaturamento();
	 	        if (ultimoNumero == null) {
	 	            ultimoNumero = 0;
	 	        }
	 	        return ultimoNumero + 1;
	 	    }
    
		
    
    
    public GetFaturamentoDTO editarFaturamento(PutFaturamentoDTO dto) {
		UUID id = dto.getIdfaturamento();
		Faturamento faturamento = faturamentoRepository.findById(id).orElseThrow();
		modelMapper.map(dto, faturamento );
		dto.setDataHoraCriacao(Instant.now());
		faturamentoRepository.save(faturamento);
		return modelMapper.map(faturamento, GetFaturamentoDTO.class);
	}

    
    public List<GetFaturamentoDTO> consultarFaturamentos(String data_inicio) throws Exception {
		List<Faturamento> faturamento = faturamentoRepository.findAll();
		List<GetFaturamentoDTO> lista = modelMapper.map(faturamento, new TypeToken<List<GetFaturamentoDTO>>() {
		}.getType());
		return lista;
	} 
    
    
    public GetFaturamentoDTO consultarUmFaturamento(UUID idFaturamento) {
        Optional<Faturamento> faturamento = faturamentoRepository.findById(idFaturamento);

        if (faturamento.isPresent()) {
        	Faturamento faturamentos = faturamento.get();
            return modelMapper.map(faturamentos, GetFaturamentoDTO.class);
        } else {
          
			throw new RuntimeException("Faturamento não encontrado"); // Lançar exceção quando não encontrada
        }
    }
    

    public GetFaturamentoDTO excluirFaturamento(UUID id) throws Exception  {
		Optional<Faturamento> faturamento = faturamentoRepository.findById(id);
		if (faturamento.isEmpty())
			throw new IllegalArgumentException("Faturamento não existe: " + id);
		// capturando o funcionario do banco de dados
	    Faturamento faturamentos = faturamento.get();
		// excluindo o funcionario no banco de dados
		faturamentoRepository.delete(faturamentos);
		GetFaturamentoDTO dto = modelMapper.map(faturamentos, GetFaturamentoDTO.class);
		dto.getIdfaturamento();
	    return dto;

	}

    public RelatorioFaturamentoDTO calcularEAtualizarTotal(UUID idFaturamento) {
        Optional<Faturamento> faturamentoOptional = faturamentoRepository.findById(idFaturamento);

        if (faturamentoOptional.isPresent()) {
            Faturamento faturamento = faturamentoOptional.get();
            BigDecimal totalValue = BigDecimal.ZERO;

            if (faturamento.getMatriculas() != null && !faturamento.getMatriculas().isEmpty()) {
                for (Matriculas matricula : faturamento.getMatriculas()) {
                    if (matricula.getValor() != null) {
                        totalValue = totalValue.add(matricula.getValor());
                    }
                }
            }

            faturamento.setTotal(totalValue);
            faturamentoRepository.save(faturamento);

            return modelMapper.map(faturamento, RelatorioFaturamentoDTO.class);
        } else {
            throw new RuntimeException("Faturamento não encontrado");
        }
    }

}



    

