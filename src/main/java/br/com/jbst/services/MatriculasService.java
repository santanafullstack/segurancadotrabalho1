package br.com.jbst.services;
import java.util.ArrayList;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetCursoDTO;
import br.com.jbst.DTO.GetFuncionarioDTO;
import br.com.jbst.DTO.GetInstrutorDTO;
import br.com.jbst.DTO.GetMatriculaDTO;
import br.com.jbst.DTO.GetPedidosDTO;
import br.com.jbst.DTO.GetTurmasDTO;
import br.com.jbst.DTO.GetUnidadeDeTreinamentoDTO;
import br.com.jbst.DTO.PostMatriculaDTO;
import br.com.jbst.DTO.PutMatriculaDTO;
import br.com.jbst.entities.Curso;
import br.com.jbst.entities.Faturamento;
import br.com.jbst.entities.Instrutor;
import br.com.jbst.entities.Matriculas;
import br.com.jbst.entities.Pedidos;
import br.com.jbst.entities.Turmas;
import br.com.jbst.entities.UnidadeDeTreinamento;
import br.com.jbst.entities.map.Funcionario;
import br.com.jbst.entities.map.PessoaFisica;
import br.com.jbst.repositories.FaturamentoRepository;
import br.com.jbst.repositories.FuncionarioRepository;
import br.com.jbst.repositories.MatriculasRepository;
import br.com.jbst.repositories.PedidosRepository;
import br.com.jbst.repositories.PessoaFisicaRepository;
import br.com.jbst.repositories.TurmasRepository;

@Service
public class MatriculasService {


	@Autowired
	PedidosService pedidosService;
	
	
	@Autowired
	MatriculasRepository matriculasRepository;
	
	@Autowired
    PessoaFisicaRepository pessoafisicaRepository;
	
	@Autowired
	TurmasRepository turmasRepository;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	PedidosRepository pedidosRepository;
	
	@Autowired
    FaturamentoRepository faturamentoRepository;
	
	@Autowired
	ModelMapper modelMapper;

	
	public GetMatriculaDTO criarMatriculas(UUID idPedidos, PostMatriculaDTO dto) {
		 UUID idMatricula = UUID.randomUUID(); // Gere o idMatricula
		Matriculas matricula = modelMapper.map(dto, Matriculas.class);
		    matricula.setIdMatricula(idMatricula);	    matricula.setIdMatricula(UUID.randomUUID());
	    matricula.setDataHoraCriacao(Instant.now()); 
	    int numeroMatricula = gerarNumeroMatricula();
        matricula.setNumeroMatricula(numeroMatricula);
	    
	    
	    Turmas turma = turmasRepository.findById(dto.getTurmas()).orElse(null); 
	    if (turma == null) {}
	    matricula.setTurmas(turma);
	    
		 Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionario()).orElse(null); 
	     if (funcionario == null) {}
	     matricula.setFuncionario(funcionario);
	    
	     PessoaFisica pessoafisica = pessoafisicaRepository.findById(dto.getPessoafisica()).orElse(null); 
	     if (pessoafisica == null) {}
	     matricula.setPessoafisica(pessoafisica);
	     
	 	
		 Faturamento faturamento = faturamentoRepository.findById(dto.getFaturamento()).orElse(null); 
	    if (faturamento == null) {}
	    matricula.setFaturamento(faturamento);
	    
	    Pedidos pedidos = pedidosRepository.findById(dto.getPedidos()).orElse(null); 
	    if (pedidos == null) {}
	    matricula.setPedidos(pedidos);
	    
	    Optional<Pedidos> pedidoOptional = pedidosRepository.findById(idPedidos);
	    if (pedidoOptional.isPresent()) {
	        Pedidos pedido = pedidoOptional.get();
	        Integer creditos = pedido.getCreditos() != null ? pedido.getCreditos() : 0;
	        Integer Matriculas = pedido.getMatriculas() != null ? pedido.getMatriculas() : 0;
	        creditos--; 
	        Matriculas++;    
	        pedido.setCreditos(creditos);
	        pedido.setMatriculas(Matriculas);
	        matricula.setPedidos(pedido);
	    } else {
	        throw new RuntimeException("Pedido não encontrado");
	    }
	    matriculasRepository.save(matricula);
	    return modelMapper.map(matricula, GetMatriculaDTO.class);
	}
	
	  private int gerarNumeroMatricula() {       
	        Integer ultimoNumero = matriculasRepository.findMaxNumeroMatricula();
	        if (ultimoNumero == null) {
	            ultimoNumero = 0;
	        }
	        return ultimoNumero + 1;
	    }
	  
	  public GetMatriculaDTO editarMatricula(PutMatriculaDTO dto) throws Exception {

			Optional<Matriculas> registro = matriculasRepository.findById(dto.getIdMatricula());
			if (registro.isEmpty())
				throw new IllegalArgumentException("Matricula' inválida: " + dto.getIdMatricula());
			Matriculas matriculas = registro.get();
			matriculas = modelMapper.map(dto, Matriculas.class);
			matriculas.setDataHoraCriacao(Instant.now());
			
		    Optional<Funcionario> funcionario = funcionarioRepository.findById(dto.getIdFuncionario());
		    if (funcionario.isPresent()) {
		        matriculas.setFuncionario(funcionario.get());
		    } else {
		        throw new IllegalArgumentException("Funcionário inválido: " + dto.getIdFuncionario());
		    }
		    
		    Optional<PessoaFisica> pessoaFisica = pessoafisicaRepository.findById(dto.getIdPessoaFisica());
		    if (pessoaFisica.isPresent()) {
		        matriculas.setPessoafisica(pessoaFisica.get());
		    } else {
		        throw new IllegalArgumentException("Pessoa Física inválida: " + dto.getIdPessoaFisica());
		    }
		    
		    Optional<Turmas> turma = turmasRepository.findById(dto.getIdTurmas());
		    if (turma.isPresent()) {
		        matriculas.setTurmas(turma.get());
		    } else {
		        throw new IllegalArgumentException("Turma inválida: " + dto.getIdTurmas());
		    }
		    
		    Optional<Pedidos> pedidos = pedidosRepository.findById(dto.getIdPedidos());
		    if (pedidos.isPresent()) {
		        matriculas.setPedidos(pedidos.get());
		    } else {
		        throw new IllegalArgumentException("Pedido inválido: " + dto.getIdPedidos());
		    }
		    
		    Optional<Faturamento> faturamento = faturamentoRepository.findById(dto.getIdFaturamento());
		    if (faturamento.isPresent()) {
		        matriculas.setFaturamento(faturamento.get());
		    } else {
		        throw new IllegalArgumentException("Faturamento inválido: " + dto.getIdFaturamento());
		    }
		    
		    matriculasRepository.save(matriculas);
			return modelMapper.map(matriculas, GetMatriculaDTO.class);
		}

	  
	  public List<GetMatriculaDTO> consultarMatriculas(String numeroMatricula) throws Exception {
			List<Matriculas> matriculas = matriculasRepository.findAllMatriculas();
			List<GetMatriculaDTO> lista = modelMapper.map(matriculas, new TypeToken<List<GetMatriculaDTO>>() {
			}.getType());
			return lista;
		} 
	  
	    public GetMatriculaDTO  consultarUmaMatricula(UUID idMatriculas) {
	        Optional<Matriculas> registro = matriculasRepository.findById(idMatriculas);

	        if (registro.isPresent()) {
	        	Matriculas matriculas = registro.get();
	            return modelMapper.map(matriculas, GetMatriculaDTO.class);
	        } else {
	          
				throw new RuntimeException("Matricula não encontrada"); // Lançar exceção quando não encontrada
	        }
	    }
	    
	   

	    

	  

	    
	}
