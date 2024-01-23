package br.com.jbst.services;

import java.math.BigDecimal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.RelatorioFaturamentoPfDto;
import br.com.jbst.DTO.RelatorioMatriculaDTO;
import br.com.jbst.DTOs.GetFaturamentoDTO;
import br.com.jbst.DTOs.GetFaturamentopfDto;
import br.com.jbst.DTOs.PostFaturamentoDTO;
import br.com.jbst.DTOs.PostFaturamentopfDto;
import br.com.jbst.DTOs.PutFaturamentopfDto;
import br.com.jbst.entities.FaturamentoPf;
import br.com.jbst.entities.Matriculas;
import br.com.jbst.entities.map.Empresa;
import br.com.jbst.entities.map.PessoaFisica;
import br.com.jbst.repositories.FaturamentopfRepository;
import br.com.jbst.repositories.PessoaFisicaRepository;
import jakarta.transaction.Transactional;
import br.com.jbst.MatriculasDTO.GetPessoaFisicaDTO;

@Service
public class FaturamentopfServices {
	
	
	@Autowired
	FaturamentopfRepository faturamentopfRepository;
	

	@Autowired
	PessoaFisicaRepository pessoafisicaRepository;
  
    @Autowired
	ModelMapper modelMapper;
    
    @Transactional
    public GetFaturamentopfDto criarFaturamentoPf(PostFaturamentopfDto dto) throws Exception {
        try {
      
        	
       	 ZoneId zone = ZoneId.systemDefault();

         // Converter para ZonedDateTime
         ZonedDateTime dataInicio = ZonedDateTime.ofInstant(dto.getData_inicio(), zone);

         // Obter a data atual sem informações de fuso horário
         LocalDate dataAtual = LocalDate.now(zone);

         // Verificar se a data de início está entre o primeiro e o último dia do mês atual
         LocalDate primeiroDiaDoMes = dataAtual.withDayOfMonth(1);
         LocalDate ultimoDiaDoMes = dataAtual.withDayOfMonth(dataAtual.lengthOfMonth());

         // Ajuste para considerar apenas a data, ignorando o horário
         LocalDate dataInicioAjustada = dataInicio.toLocalDate();

         if (dataInicioAjustada.isBefore(primeiroDiaDoMes) || dataInicioAjustada.isAfter(ultimoDiaDoMes)) {
             throw new Exception("A data de início deve estar entre o primeiro e o último dia do mês atual. Data de início: " + dataInicio);
         }
         
         
         
        // Verificar se já foi feito um faturamento no período daquele mês para a empresa
        if (existsFaturamentoNoPeriodo(dto.getIdpessoafisica(), dto.getData_inicio(), dto.getData_fim())) {
            throw new Exception("Já foi cadastrado um faturamento para a empresa no período especificado.");
        }
        	
        	
            PessoaFisica pessoaFisica = pessoafisicaRepository.findById(dto.getIdpessoafisica())
                    .orElseThrow(() -> new RuntimeException("Pessoa Fisica não encontrada com o ID: " + dto.getIdpessoafisica()));

            // Criar o objeto FaturamentoPf e atribuir a pessoa física
            FaturamentoPf faturamentoPf = new FaturamentoPf();
            faturamentoPf.setIdfaturamentopf(UUID.randomUUID());
            faturamentoPf.setDataHoraCriacao(Instant.now());
            faturamentoPf.setData_inicio(dto.getData_inicio());
            faturamentoPf.setData_fim(dto.getData_fim());
            faturamentoPf.setNumeroFaturamento(gerarNumeroFaturamentoPf());
            faturamentoPf.setFaturaFechada(false);
            faturamentoPf.setPessoaFisica(pessoaFisica);
            faturamentoPf.setVenda(dto.getVenda());
            faturamentoPf.setObservacoes(dto.getObservacoes());
            faturamentoPf.setTotal(null); // Ou defina um valor inicial, se necessário

            // Salvar o faturamento
            faturamentopfRepository.save(faturamentoPf);

            return modelMapper.map(faturamentoPf, GetFaturamentopfDto.class);
        } catch (Exception e) {
            // Log ou manipule a exceção conforme necessário
            throw new RuntimeException("Erro ao criar faturamento para pessoa física.", e);
        }
    }


private boolean existsFaturamentoNoPeriodo(UUID idPessoaFisica, Instant dataInicio, Instant dataFim) {
    // Consulta no banco de dados para verificar se já existe um faturamento
    // no período daquele mês para a pessoa física
    return faturamentopfRepository.existsPessoaFisicaNoPeriodo(idPessoaFisica, dataInicio, dataFim);
}

    private int gerarNumeroFaturamentoPf() {
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
	    List<GetFaturamentopfDto> lista = mapFaturamentopfListToDTO(faturamentopf);
	    return lista;
	}

	private List<GetFaturamentopfDto> mapFaturamentopfListToDTO(List<FaturamentoPf> faturamentopfList) {
	    return faturamentopfList.stream()
	        .map(this::mapFaturamentoToDTO)
	        .collect(Collectors.toList());
	}

    public GetFaturamentopfDto consultarUmFaturamento(UUID idFaturamento) throws Exception {
        Optional<FaturamentoPf> faturamentoOptional = faturamentopfRepository.findById(idFaturamento);

        if (faturamentoOptional.isPresent()) {
            FaturamentoPf faturamento = faturamentoOptional.get();
            return mapFaturamentoToDTO(faturamento);
        } else {
            return null; // Or throw an exception, return an error DTO, etc.
        }
    }

    // Your existing method for mapping Faturamento to DTO
    private GetFaturamentopfDto mapFaturamentoToDTO(FaturamentoPf faturamento) {
        GetFaturamentopfDto dto = new GetFaturamentopfDto();
        dto.setIdfaturamentopf(faturamento.getIdfaturamentopf());
        dto.setNumeroFaturamento(faturamento.getNumeroFaturamento());
        dto.setDataHoraCriacao(faturamento.getDataHoraCriacao());
        dto.setData_inicio(faturamento.getData_inicio());
        dto.setData_fim(faturamento.getData_fim());
        dto.setMatriculas(mapMatriculasToDTO(faturamento.getMatriculas()));
        dto.setTotal(faturamento.getTotal());
        dto.setPessoafisica(mapPessoaFisicaToDTO(faturamento.getPessoaFisica()));
        return dto;
    }

    // Method to map Matriculas to DTO (Assuming you have a method mapMatriculasToDTO)
    private List<RelatorioMatriculaDTO> mapMatriculasToDTO(List<Matriculas> matriculas) {
		return null;
        // Implement this method based on your requirements
    }

    // Method to map PessoaFisica to DTO
    private GetPessoaFisicaDTO mapPessoaFisicaToDTO(PessoaFisica pessoaFisica) {
    	
    	 if (pessoaFisica == null) {
    	        return null;  // Or create and return a default DTO if needed
    	    }
        GetPessoaFisicaDTO pessoaFisicaDTO = new GetPessoaFisicaDTO();
        pessoaFisicaDTO.setIdpessoafisica(pessoaFisica.getIdpessoafisica());
        pessoaFisicaDTO.setDataHoraCriacao(pessoaFisica.getDataHoraCriacao());
        pessoaFisicaDTO.setPessoafisica(pessoaFisica.getPessoafisica());
        pessoaFisicaDTO.setRg(pessoaFisica.getRg());
        pessoaFisicaDTO.setCpf(pessoaFisica.getCpf());
        pessoaFisicaDTO.setTelefone_1(pessoaFisica.getTelefone1());
        pessoaFisicaDTO.setTelefone_2(pessoaFisica.getTelefone1());
        pessoaFisicaDTO.setEmail(pessoaFisica.getEmail());
        pessoaFisicaDTO.setAssinatura_pessoafisica(pessoaFisica.getAssinaturaPessoaFisica());
        return pessoaFisicaDTO;
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


