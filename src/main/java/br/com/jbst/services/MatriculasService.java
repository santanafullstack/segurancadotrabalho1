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
import br.com.jbst.MatriculasDTO.GetMatriculaFaturamentoPfDTO;
import br.com.jbst.MatriculasDTO.GetMatriculaFaturamentoPjDTO;
import br.com.jbst.MatriculasDTO.GetMatriculaPedidosDTO;
import br.com.jbst.MatriculasDTO.PostMatriculaFaturamentoPfDTO;
import br.com.jbst.MatriculasDTO.PostMatriculaFaturamentoPjDTO;
import br.com.jbst.MatriculasDTO.PostMatriculaPedidosDTO;
import br.com.jbst.MatriculasDTO.PutMatriculaFaturamentoPfDTO;
import br.com.jbst.MatriculasDTO.PutMatriculaFaturamentoPjDTO;
import br.com.jbst.MatriculasDTO.PutMatriculaPedidosDTO;
import br.com.jbst.entities.Curso;
	import br.com.jbst.entities.Faturamento;
	import br.com.jbst.entities.FaturamentoPf;
	import br.com.jbst.entities.Instrutor;
	import br.com.jbst.entities.Matriculas;
	import br.com.jbst.entities.Pedidos;
	import br.com.jbst.entities.Turmas;
	import br.com.jbst.entities.UnidadeDeTreinamento;
	import br.com.jbst.entities.map.Funcionario;
	import br.com.jbst.entities.map.PessoaFisica;
	import br.com.jbst.repositories.FaturamentoRepository;
	import br.com.jbst.repositories.FaturamentopfRepository;
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
	    FaturamentopfRepository faturamentopfRepository;
		
		@Autowired
		ModelMapper modelMapper;
	
			
		//1
		public GetMatriculaFaturamentoPjDTO criarMatriculasFaturamentoPj( PostMatriculaFaturamentoPjDTO dto) {
			UUID idMatricula = UUID.randomUUID(); 
			Matriculas matricula = modelMapper.map(dto, Matriculas.class);
			matricula.setIdMatricula(idMatricula);	    matricula.setIdMatricula(UUID.randomUUID());
		    matricula.setDataHoraCriacao(Instant.now()); 
		    int numeroMatricula = gerarNumeroMatricula();
	        matricula.setNumeroMatricula(numeroMatricula);    
		    Turmas turma = turmasRepository.findById(dto.getIdTurmas()).orElse(null); 
		    if (turma == null) {}
		    matricula.setTurmas(turma);
		    
			Turmas turmas = turmasRepository.findById(dto.getIdTurmas()).orElse(null); 
		    if (turma == null) {}
		    matricula.setTurmas(turmas);
			
			 Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionario()).orElse(null); 
		     if (funcionario == null) {}
		     matricula.setFuncionario(funcionario);
		    
		 	
			 Faturamento faturamento = faturamentoRepository.findById(dto.getFaturamento()).orElse(null); 
		    if (faturamento == null) {}
		    matricula.setFaturamento(faturamento);
		    
		    
		    matriculasRepository.save(matricula);
		    return modelMapper.map(matricula, GetMatriculaFaturamentoPjDTO.class);
		}	
			
		//2
		public  GetMatriculaFaturamentoPjDTO editarMatriculaPj(PutMatriculaFaturamentoPjDTO dto) throws Exception {
	
				Optional<Matriculas> registro = matriculasRepository.findById(dto.getIdMatricula());
				if (registro.isEmpty())
					throw new IllegalArgumentException("Matricula' inválida: " + dto.getIdMatricula());
				Matriculas matriculas = registro.get();
				matriculas = modelMapper.map(dto, Matriculas.class);
				matriculas.setDataHoraCriacao(Instant.now());
				
			    Optional<Funcionario> funcionario = funcionarioRepository.findById(dto.getFuncionario());
			    if (funcionario.isPresent()) {
			        matriculas.setFuncionario(funcionario.get());
			    } else {
			        throw new IllegalArgumentException("Funcionário inválido: " + dto.getFuncionario());
			    }
			    
			
			    
			    Optional<Turmas> turma = turmasRepository.findById(dto.getTurmas());
			    if (turma.isPresent()) {
			        matriculas.setTurmas(turma.get());
			    } else {
			        throw new IllegalArgumentException("Turma inválida: " + dto.getTurmas());
			    }	 
			    
			    Optional<Faturamento> faturamento = faturamentoRepository.findById(dto.getFaturamento());
			    if (faturamento.isPresent()) {
			        matriculas.setFaturamento(faturamento.get());
			    } else {
			        throw new IllegalArgumentException("Faturamento inválido: " + dto.getFaturamento());
			    }
			    
			    matriculasRepository.save(matriculas);
				return modelMapper.map(matriculas,  GetMatriculaFaturamentoPjDTO.class);
			}
			
		//3
		public GetMatriculaFaturamentoPfDTO criarMatriculasFaturamentoPf( PostMatriculaFaturamentoPfDTO dto) {
			UUID idMatricula = UUID.randomUUID(); 
			Matriculas matricula = modelMapper.map(dto, Matriculas.class);
			matricula.setIdMatricula(idMatricula);	    matricula.setIdMatricula(UUID.randomUUID());
		    matricula.setDataHoraCriacao(Instant.now()); 
		    int numeroMatricula = gerarNumeroMatricula();
	        matricula.setNumeroMatricula(numeroMatricula);    
		    Turmas turma = turmasRepository.findById(dto.getIdTurmas()).orElse(null); 
		    if (turma == null) {}
		    matricula.setTurmas(turma);
		    
			Turmas turmas = turmasRepository.findById(dto.getIdTurmas()).orElse(null); 
		    if (turma == null) {}
		    matricula.setTurmas(turmas);
			
		     PessoaFisica pessoafisica = pessoafisicaRepository.findById(dto.getIdpessoafisica()).orElse(null); 
	         if (pessoafisica == null) {}
	         matricula.setPessoafisica(pessoafisica);
		    
		 	
	         FaturamentoPf faturamento = faturamentopfRepository.findById(dto.getIdfaturamentopf()).orElse(null); 
		    if (faturamento == null) {}
		    matricula.setFaturamentopf(faturamento);
		    
		    
		    matriculasRepository.save(matricula);
		    return modelMapper.map(matricula, GetMatriculaFaturamentoPfDTO.class);
		}	
			
		//4
		public  GetMatriculaFaturamentoPfDTO editarMatriculaPf(PutMatriculaFaturamentoPfDTO dto) throws Exception {
	
				Optional<Matriculas> registro = matriculasRepository.findById(dto.getIdMatricula());
				if (registro.isEmpty())
					throw new IllegalArgumentException("Matricula' inválida: " + dto.getIdMatricula());
				Matriculas matriculas = registro.get();
				matriculas = modelMapper.map(dto, Matriculas.class);
				matriculas.setDataHoraCriacao(Instant.now());
				
	    Optional<PessoaFisica> pessoaFisica = pessoafisicaRepository.findById(dto.getIdpessoafisica());
		    if (pessoaFisica.isPresent()) {
		        matriculas.setPessoafisica(pessoaFisica.get());
		    } else {
		        throw new IllegalArgumentException("Pessoa Física inválida: " + dto.getIdpessoafisica());
		    }
			
			    
			    Optional<Turmas> turma = turmasRepository.findById(dto.getTurmas());
			    if (turma.isPresent()) {
			        matriculas.setTurmas(turma.get());
			    } else {
			        throw new IllegalArgumentException("Turma inválida: " + dto.getTurmas());
			    }	 
			    
			    Optional<FaturamentoPf> faturamento = faturamentopfRepository.findById(dto.getIdfaturamentopf());
			    if (faturamento.isPresent()) {
			        matriculas.setFaturamentopf(faturamento.get());
			    } else {
			        throw new IllegalArgumentException("Faturamento inválido: " + dto.getIdfaturamentopf());
			    }
			    
			    matriculasRepository.save(matriculas);
				return modelMapper.map(matriculas,  GetMatriculaFaturamentoPfDTO.class);
			}		
		//5
		public GetMatriculaPedidosDTO criarMatriculasPedidos(UUID idPedidos, PostMatriculaPedidosDTO dto) {
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
			    
	    
			    Optional<Pedidos> pedidoOptional = pedidosRepository.findById(idPedidos);
			    if (pedidoOptional.isPresent()) {
			        Pedidos pedido = pedidoOptional.get();
			        Integer creditos = pedido.getCreditos() != null ? pedido.getCreditos() : 0;
			        Integer Matriculas = pedido.getMatriculasrealizadas() != null ? pedido.getMatriculasrealizadas() : 0;
			        creditos--; 
			        Matriculas++;    
			        pedido.setCreditos(creditos);
			        pedido.setMatriculasrealizadas(Matriculas);
			        matricula.setPedidos(pedido);
			    } else {
			        throw new RuntimeException("Pedido não encontrado");
			    }
			    matriculasRepository.save(matricula);
			    return modelMapper.map(matricula, GetMatriculaPedidosDTO.class);
			}
		 //6
	public GetMatriculaPedidosDTO editarMatriculaPedidos(PutMatriculaPedidosDTO dto) throws Exception {
		
					Optional<Matriculas> registro = matriculasRepository.findById(dto.getIdMatricula());
					if (registro.isEmpty())
						throw new IllegalArgumentException("Matricula' inválida: " + dto.getIdMatricula());
					Matriculas matriculas = registro.get();
					matriculas = modelMapper.map(dto, Matriculas.class);
					matriculas.setDataHoraCriacao(Instant.now());
					
				    Optional<Funcionario> funcionario = funcionarioRepository.findById(dto.getFuncionario());
				    if (funcionario.isPresent()) {
				        matriculas.setFuncionario(funcionario.get());
				    } else {
				        throw new IllegalArgumentException("Funcionário inválido: " + dto.getFuncionario());
				    }
				    
				
				    
				    Optional<Turmas> turma = turmasRepository.findById(dto.getTurmas());
				    if (turma.isPresent()) {
				        matriculas.setTurmas(turma.get());
				    } else {
				        throw new IllegalArgumentException("Turma inválida: " + dto.getTurmas());
				    }
				    
				    Optional<Pedidos> pedidos = pedidosRepository.findById(dto.getPedidos());
				    if (pedidos.isPresent()) {
				        matriculas.setPedidos(pedidos.get());
				    } else {
				        throw new IllegalArgumentException("Pedido inválido: " + dto.getPedidos());
				    }
				 
				    
				    matriculasRepository.save(matriculas);
					return modelMapper.map(matriculas, GetMatriculaPedidosDTO.class);
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
		    	    
			  private int gerarNumeroMatricula() {       
			        Integer ultimoNumero = matriculasRepository.findMaxNumeroMatricula();
			        if (ultimoNumero == null) {
			            ultimoNumero = 0;
			        }
			        return ultimoNumero + 1;
			    }
		  
	
		    
		}
