package br.com.jbst.services;
import java.util.ArrayList;

import java.rmi.NotBoundException;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.commons.logging.Log;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.jbst.DTO.GetMatriculaDTO;
import br.com.jbst.MatriculasDTO.AdicionarUsuariosMatriculaDTO;
import br.com.jbst.MatriculasDTO.GetMatriculaFaturamentoPfDTO;
import br.com.jbst.MatriculasDTO.GetMatriculaFaturamentoPjDTO;
import br.com.jbst.MatriculasDTO.GetMatriculaPedidosDTO;
import br.com.jbst.MatriculasDTO.PostMatriculaFaturamentoPfDTO;
import br.com.jbst.MatriculasDTO.PostMatriculaFaturamentoPjDTO;
import br.com.jbst.MatriculasDTO.PostMatriculaPedidosDTO;
import br.com.jbst.MatriculasDTO.PutMatriculaFaturamentoPfDTO;
import br.com.jbst.MatriculasDTO.PutMatriculaFaturamentoPjDTO;
import br.com.jbst.MatriculasDTO.PutMatriculaPedidosDTO;
import br.com.jbst.entities.Faturamento;
import br.com.jbst.entities.FaturamentoPf;
import br.com.jbst.entities.Matriculas;
import br.com.jbst.entities.Pedidos;
import br.com.jbst.entities.Usuario;
import br.com.jbst.entities.Turmas;
import br.com.jbst.entities.map.Empresa;
import br.com.jbst.entities.map.Funcionario;
import br.com.jbst.entities.map.PessoaFisica;
import br.com.jbst.repositories.FaturamentoRepository;
import br.com.jbst.repositories.FaturamentopfRepository;
import br.com.jbst.repositories.FuncionarioRepository;
import br.com.jbst.repositories.MatriculasRepository;
import br.com.jbst.repositories.PedidosRepository;
import br.com.jbst.repositories.PessoaFisicaRepository;
import br.com.jbst.repositories.TurmasRepository;
import br.com.jbst.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import br.com.jbst.entities.map.Funcionario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class MatriculasService {

	@Autowired
	PedidosService pedidosService;

	@Autowired
	UsuarioRepository usuarioRepository;

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
	
		@Transactional
		public GetMatriculaFaturamentoPjDTO criarMatriculaFaturamentoPj(PostMatriculaFaturamentoPjDTO dto) {
		    try {
		        // Gere um ID para a matrícula
		        UUID idMatricula = UUID.randomUUID();
	
		        // Crie uma nova instância de Matriculas e atribua o ID gerado e a data/hora de criação
		        Matriculas matricula = new Matriculas();
		        matricula.setIdMatricula(idMatricula);
		        matricula.setDataHoraCriacao(Instant.now());
	
		        // Mapeie os dados do DTO para a entidade Matriculas
		        modelMapper.map(dto, matricula);
	
		        // Gere um número de matrícula
		        int numeroMatricula = gerarNumeroMatricula();
		        matricula.setNumeroMatricula(numeroMatricula);
	
		        // Busque a turma no repositório
		        Turmas turma = turmasRepository.findById(dto.getIdTurmas())
		                .orElseThrow(() -> new NotFoundException());
	
		        // Busque o funcionário no repositório
		        Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionario())
		                .orElseThrow(() -> new NotFoundException());
	
		        // Verifique se o funcionário já está matriculado nesta turma
		        boolean funcionarioJaMatriculado = matriculasRepository.existsByFuncionarioAndTurmas(funcionario, turma);
	
		        if (funcionarioJaMatriculado) {
		            throw new TurmaAlreadyExistsException("Este funcionário já está matriculado nesta turma.");
		        }
	
		        // Busque o faturamento no repositório
		        Faturamento faturamento = faturamentoRepository.findById(dto.getFaturamento())
		                .orElseThrow(() -> new NotFoundException());
	
		        // Verifique se o faturamento está fechado
		        if (faturamento.isFaturaFechada()) {
		            throw new Exception("Não é possível criar uma matrícula com faturamento fechado.");
		        }
		
		     // Busque o funcionário no repositório
		        Funcionario funcionario1 = funcionarioRepository.findById(dto.getFuncionario())
		                .orElseThrow(() -> new NotFoundException());
	
		        // Busque o faturamento no repositório
		        Faturamento faturamento1 = faturamentoRepository.findById(dto.getFaturamento())
		                .orElseThrow(() -> new NotFoundException());
	
		        // Verifique se o funcionário pertence à mesma empresa do faturamento
		        if (!funcionario1.getEmpresa().getIdEmpresa().equals(faturamento1.getEmpresa().getIdEmpresa())) {
		            throw new RuntimeException("O funcionário não pertence à mesma empresa do faturamento.");
		        }
	
		        // Restante do código...
	
	
	
		        // Associe a turma e o funcionário à matrícula
		        matricula.setTurmas(turma);
		        matricula.setFuncionario(funcionario);
	
		        // Associe o faturamento à matrícula
		        matricula.setFaturamento(faturamento);
	
		        // Verifique e feche a matrícula se necessário
		        faturamento.fecharMatriculasAposDataFim();
	
		        // Busque o usuário no repositório
		        Usuario usuario = usuarioRepository.findById(dto.getId())
		                .orElseThrow(() -> new NotFoundException());
	
		        // Inicialize a lista matriculasUsuarios se for nula
		        if (usuario.getMatriculas() == null) {
		            usuario.setMatriculas(new ArrayList<>());
		        }
	
		        // Adicione a matrícula ao usuário
		        usuario.getMatriculas().add(matricula);
	
		        // Salve a matrícula no repositório
		        matricula = matriculasRepository.save(matricula);
	
		        // Salve novamente o usuário para persistir a associação com Matriculas
		        usuario = usuarioRepository.save(usuario);
	
		        // Mapeie a entidade matricula para o DTO de resposta
		        return modelMapper.map(matricula, GetMatriculaFaturamentoPjDTO.class);
		    } catch (NotFoundException e) {
		        // Log ou manipule a exceção conforme necessário
		        throw new RuntimeException("Erro ao criar matrícula com faturamento PJ. Detalhes: " + e.getMessage(), e);
		    } catch (TurmaAlreadyExistsException e) {
		        // Log ou manipule a exceção conforme necessário
		        throw e;
		    } catch (Exception e) {
		        // Log ou manipule a exceção conforme necessário
		        e.printStackTrace(); // Adiciona esta linha para imprimir a stack trace da exceção
		        throw new RuntimeException("Erro ao criar matrícula com faturamento PJ. Detalhes: " + e.getMessage(), e);
		    }
	
		}


	public class TurmaAlreadyExistsException extends RuntimeException {
	    public TurmaAlreadyExistsException(String message) {
	        super(message);
	    }
	}


	// 2
	public GetMatriculaFaturamentoPjDTO editarMatriculaPj(PutMatriculaFaturamentoPjDTO dto) throws Exception {
		Optional<Matriculas> registro = matriculasRepository.findById(dto.getIdMatricula());

		if (registro.isEmpty()) {
			throw new IllegalArgumentException("Matrícula inválida: " + dto.getIdMatricula());
		}

		Matriculas matriculas = registro.get();

		modelMapper.map(dto, matriculas); // Utiliza o ModelMapper para mapear os dados do DTO para a entidade

		matriculas.setDataHoraCriacao(Instant.now());

		Optional<Funcionario> funcionario = funcionarioRepository.findById(dto.getIdFuncionario());

		if (funcionario.isPresent()) {
			matriculas.setFuncionario(funcionario.get());
		} else {
			throw new IllegalArgumentException("Funcionário inválido: " + dto.getIdFuncionario());
		}

		Optional<Turmas> turma = turmasRepository.findById(dto.getIdTurmas());

		if (turma.isPresent()) {
			matriculas.setTurmas(turma.get());
		} else {
			throw new IllegalArgumentException("Turma inválida: " + dto.getIdTurmas());
		}

		Optional<Faturamento> faturamento = faturamentoRepository.findById(dto.getIdfaturamento());

		if (faturamento.isPresent()) {
			matriculas.setFaturamento(faturamento.get());
		} else {
			throw new IllegalArgumentException("Faturamento inválido: " + dto.getIdfaturamento());
		}

		matriculasRepository.save(matriculas);
		return modelMapper.map(matriculas, GetMatriculaFaturamentoPjDTO.class);
	}
	

	@Transactional
	public GetMatriculaFaturamentoPfDTO criarMatriculasFaturamentoPf(PostMatriculaFaturamentoPfDTO matriculaDTO) {
	    try {
	        Matriculas matricula = modelMapper.map(matriculaDTO, Matriculas.class);

	        // Gere um número de matrícula
	        int numeroMatricula = gerarNumeroMatricula();
	        matricula.setNumeroMatricula(numeroMatricula);
	        
	        Turmas turma = turmasRepository.findById(matriculaDTO.getIdTurmas())
	                .orElseThrow(() -> new AccountNotFoundException("Turma não encontrada com ID: " + matriculaDTO.getIdTurmas()));

	        PessoaFisica pessoaFisica = pessoafisicaRepository.findById(matriculaDTO.getIdpessoafisica())
	                .orElseThrow(() -> new AccountNotFoundException("Pessoa física não encontrada com ID: " + matriculaDTO.getIdpessoafisica()));

	        FaturamentoPf faturamentoPf = faturamentopfRepository.findById(matriculaDTO.getIdfaturamentopf())
	                .orElseThrow(() -> new AccountNotFoundException("FaturamentoPF não encontrado com ID: " + matriculaDTO.getIdfaturamentopf()));

	        // Busque o funcionário no repositório
	        PessoaFisica pessoaFisica1 = pessoafisicaRepository.findById(matriculaDTO.getIdpessoafisica())
	                .orElseThrow(() -> new NotFoundException());

	        // Verifique se o funcionário já está matriculado nesta turma
	        boolean pessoaFisicaJaMatriculado = matriculasRepository.existsByPessoafisicaAndTurmas(pessoaFisica1, turma);

	        if (pessoaFisicaJaMatriculado) {
	            throw new TurmaAlreadyExistsException("Esta Pessoa  já está matriculado nesta turma.");
	        }

	        
	        // Busque o faturamento no repositório
	        FaturamentoPf faturamento = faturamentopfRepository.findById(matriculaDTO.getIdfaturamentopf())
	                .orElseThrow(() -> new NotFoundException());

	        // Verifique se o faturamento está fechado
	        if (faturamento.isFaturaFechada()) {
	            throw new Exception("Não é possível criar uma matrícula com faturamento fechado.");
	        }
	        
	        // Verificar duplicidade de CPF na turma
	        boolean cpfDuplicadoNaTurma = matriculasRepository.existsByTurmasAndPessoafisica_Cpf(turma, pessoaFisica.getCpf());

	        if (cpfDuplicadoNaTurma) {
	            throw new Exception("Duplicidade de CPF na turma.");
	        }

	        UUID idPessoaFisicaAssociadaAoFaturamento = faturamentoPf.getPessoaFisica().getIdpessoafisica();

	     // Verificar se o ID da pessoa física na matrícula corresponde ao ID associado ao faturamento
	     if (!pessoaFisica.getIdpessoafisica().equals(idPessoaFisicaAssociadaAoFaturamento)) {
	         throw new Exception("O ID da pessoa física na matrícula não corresponde ao ID associado ao faturamento.");
	     }
	        // Configurar as associações na entidade Matriculas
	        matricula.setIdMatricula(UUID.randomUUID());
	        matricula.setDataHoraCriacao(Instant.now());
	        matricula.setTurmas(turma);
	        matricula.setPessoafisica(pessoaFisica);
	        matricula.setFaturamentopf(faturamentoPf);

	        // Obter o usuário com base no ID fornecido no DTO
	        Usuario usuario = usuarioRepository.findById(matriculaDTO.getId())
	                .orElseThrow(() -> new NotBoundException("Usuário não encontrado com ID: " + matriculaDTO.getId()));

	        // Inicializar a lista de matrículas do usuário se for nula
	        if (usuario.getMatriculas() == null) {
	            usuario.setMatriculas(new ArrayList<>());
	        }

	        // Adicionar a matrícula ao usuário
	        usuario.getMatriculas().add(matricula);

	        // Salvar a matrícula para persistir as associações
	        matriculasRepository.save(matricula);

	        // Salvar o usuário novamente para persistir a associação com Matriculas
	        usuarioRepository.save(usuario);

	        // Mapear a entidade Matriculas para DTO usando ModelMapper
	        return modelMapper.map(matricula, GetMatriculaFaturamentoPfDTO.class);
	    } catch (Exception e) {
	        // Logar a exceção ou lançar uma exceção mais específica, se necessário
	        throw new RuntimeException("Já existe uma matricula nesta Turma para este cpf.", e);
	    }
	}



	// 4
	public GetMatriculaFaturamentoPfDTO editarMatriculaPf(PutMatriculaFaturamentoPfDTO dto) throws Exception {
		Optional<Matriculas> registro = matriculasRepository.findById(dto.getIdMatricula());

		if (registro.isEmpty()) {
			throw new IllegalArgumentException("Matrícula inválida: " + dto.getIdMatricula());
		}

		Matriculas matriculas = registro.get();
		// Atualiza apenas os campos que não são IDs
		modelMapper.map(dto, matriculas);
		matriculas.setDataHoraCriacao(Instant.now());

		// Atualiza Pessoa Física
		Optional<PessoaFisica> pessoaFisica = pessoafisicaRepository.findById(dto.getIdpessoafisica());
		if (pessoaFisica.isPresent()) {
			matriculas.setPessoafisica(pessoaFisica.get());
		} else {
			throw new IllegalArgumentException("Pessoa Física inválida: " + dto.getIdpessoafisica());
		}

		// Atualiza Turma
		Optional<Turmas> turma = turmasRepository.findById(dto.getIdTurmas());
		if (turma.isPresent()) {
			matriculas.setTurmas(turma.get());
		} else {
			throw new IllegalArgumentException("Turma inválida: " + dto.getIdTurmas());
		}

		// Atualiza Faturamento
		Optional<FaturamentoPf> faturamento = faturamentopfRepository.findById(dto.getIdfaturamentopf());
		if (faturamento.isPresent()) {
			matriculas.setFaturamentopf(faturamento.get());
		} else {
			throw new IllegalArgumentException("Faturamento inválido: " + dto.getIdfaturamentopf());
		}

		matriculasRepository.save(matriculas);
		return modelMapper.map(matriculas, GetMatriculaFaturamentoPfDTO.class);
	}

	@Transactional
	public GetMatriculaPedidosDTO criarMatriculasPedidos(UUID idPedidos, PostMatriculaPedidosDTO dto) {
	    try {
	        // Gere um ID único para a matrícula
	        UUID idMatricula = UUID.randomUUID();

	        // Mapeie os dados do DTO para a entidade Matriculas
	        Matriculas matricula = modelMapper.map(dto, Matriculas.class);

	        // Configure os atributos da matrícula
	        matricula.setIdMatricula(idMatricula);
	        matricula.setDataHoraCriacao(Instant.now());
	        int numeroMatricula = gerarNumeroMatricula();
	        matricula.setNumeroMatricula(numeroMatricula);

	        // Busque a turma no repositório
	        Turmas turma = turmasRepository.findById(dto.getIdTurmas()).orElse(null);
	        if (turma == null) {
	            // Trate a situação em que a turma não é encontrada
	            // Pode lançar uma exceção ou retornar uma resposta adequada
	        }

	        // Verifique se o funcionário já está matriculado nesta turma
	        Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionario())
	                .orElseThrow(() -> new NotFoundException());
	        boolean funcionarioJaMatriculado = matriculasRepository.existsByFuncionarioAndTurmas(funcionario, turma);
	        if (funcionarioJaMatriculado) {
	            throw new TurmaAlreadyExistsException("Este funcionário já está matriculado nesta turma.");
	        }

	        // Configure a turma e o funcionário na matrícula
	        matricula.setTurmas(turma);
	        matricula.setFuncionario(funcionario);

	        // Busque o pedido no repositório
	        Optional<Pedidos> pedidoOptional = pedidosRepository.findById(idPedidos);
	        if (pedidoOptional.isPresent()) {
	            // Prossiga com a lógica para associar usuários à matrícula
	            Pedidos pedido = pedidoOptional.get();
	            Integer creditos = pedido.getCreditos() != null ? pedido.getCreditos() : 0;
	            Integer matriculasRealizadas = pedido.getMatriculasrealizadas() != null ? pedido.getMatriculasrealizadas() : 0;
	            creditos--;
	            matriculasRealizadas++;
	            pedido.setCreditos(creditos);
	            pedido.setMatriculasrealizadas(matriculasRealizadas);
	            matricula.setPedidos(pedido);

	            // Busque o usuário no repositório
	            Usuario usuario = usuarioRepository.findById(dto.getId()).orElseThrow(() -> new NotFoundException());

	            // Inicialize a lista de usuários se for nula
	            if (matricula.getUsuarios() == null) {
	                matricula.setUsuarios(new ArrayList<>());
	            }

	            // Adicione a matrícula ao usuário
	            matricula.getUsuarios().add(usuario);

	            // Salve novamente a matrícula para persistir a associação com usuários
	            matricula = matriculasRepository.save(matricula);
	        } else {
	            throw new RuntimeException("Pedido não encontrado");
	        }

	        // Converta a entidade Matriculas para DTO e retorne
	        return modelMapper.map(matricula, GetMatriculaPedidosDTO.class);
	    } catch (Exception e) {
	        // Log ou manipule a exceção conforme necessário
	        throw new RuntimeException("Este Funcionário já está matriculado nesta Turma.", e);
	    }
	}



	// 6
	public GetMatriculaPedidosDTO editarMatriculaPedidos(UUID idPedidos, UUID idMatricula, PutMatriculaPedidosDTO dto) {
		// Encontrar a matrícula pelo ID
		Optional<Matriculas> matriculaOptional = matriculasRepository.findById(idMatricula);

		if (matriculaOptional.isPresent()) {
			Matriculas matricula = matriculaOptional.get();

			// Atualizar os campos da matrícula com os valores do DTO
			modelMapper.map(dto, matricula);

			// Encontrar a turma pelo ID e definir na matrícula
			Turmas turma = turmasRepository.findById(dto.getIdTurmas()).orElse(null);
			if (turma != null) {
				matricula.setTurmas(turma);
			} else {
				throw new RuntimeException("Turma não encontrada");
			}

			// Encontrar o funcionário pelo ID e definir na matrícula
			UUID idFuncionario = dto.getIdFuncionario();
			Funcionario funcionario = funcionarioRepository.findById(idFuncionario).orElse(null);
			if (funcionario != null) {
				matricula.setFuncionario(funcionario);
			} else {
				throw new RuntimeException("Funcionário não encontrado");
			}

			// Encontrar o pedido pelo ID e atualizar os campos
			Optional<Pedidos> pedidoOptional = pedidosRepository.findById(idPedidos);
			if (pedidoOptional.isPresent()) {
				Pedidos pedido = pedidoOptional.get();
				Integer creditos = pedido.getCreditos() != null ? pedido.getCreditos() : 0;
				Integer matriculasRealizadas = pedido.getMatriculasrealizadas() != null
						? pedido.getMatriculasrealizadas()
						: 0;
				creditos--;
				matriculasRealizadas++;
				pedido.setCreditos(creditos);
				pedido.setMatriculasrealizadas(matriculasRealizadas);
				matricula.setPedidos(pedido);
			} else {
				throw new RuntimeException("Pedido não encontrado");
			}

			// Salvar a matrícula atualizada
			matriculasRepository.save(matricula);

			// Retornar a matrícula mapeada para o DTO desejado
			return modelMapper.map(matricula, GetMatriculaPedidosDTO.class);
		} else {
			throw new RuntimeException("Matrícula não encontrada");
		}
	}

	public List<GetMatriculaDTO> consultarMatriculas(String numeroMatricula) throws Exception {
		List<Matriculas> matriculas = matriculasRepository.findAllMatriculas();
		List<GetMatriculaDTO> lista = modelMapper.map(matriculas, new TypeToken<List<GetMatriculaDTO>>() {
		}.getType());
		return lista;
	}

	public GetMatriculaDTO consultarUmaMatricula(UUID idMatriculas) {
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

	// 7 - Adicionar Usuários

	
	public GetMatriculaDTO adicionarUsuariosMatricula(AdicionarUsuariosMatriculaDTO dto) throws Exception {
	    try {
	        UUID matriculaId = dto.getIdMatricula();
	        Matriculas matricula = matriculasRepository.findById(matriculaId)
	                .orElseThrow(() -> new NoSuchElementException("Matricula não encontrada com o ID: " + matriculaId));
	        modelMapper.map(dto, matricula);
	        List<Usuario> usuarios = obterUsuariosPorIds(dto.getIdsUsuarios());

	        // Salva a matrícula separadamente
	        matricula = matriculasRepository.save(matricula);

	        // Adiciona os usuários à matrícula
	        matricula.getUsuarios().addAll(usuarios);
	        matriculasRepository.save(matricula);

	        return modelMapper.map(matricula, GetMatriculaDTO.class);
	    } catch (Exception ex) {
	        // Trate a exceção de forma apropriada, registre logs, etc.
	        throw new Exception("Erro ao incluir Matriculas.", ex);
	    }
	}

	private List<Usuario> obterUsuariosPorIds(List<UUID> usuarioIds) {
	    List<Usuario> usuarios = new ArrayList<>();

	    if (usuarioIds != null && !usuarioIds.isEmpty()) {
	        for (UUID id : usuarioIds) {
	            Usuario usuario = usuarioRepository.findById(id)
	                    .orElseThrow(() -> new NoSuchElementException("Usuario não encontrado com o ID: " + id));

	            usuarios.add(usuario);
	        }
	    }

	    return usuarios;
	}
	
	public GetMatriculaDTO excluirUsuariosMatricula(UUID matriculaId, List<UUID> usuarioIds) throws Exception {
	    try {
	        Matriculas matricula = matriculasRepository.findById(matriculaId)
	                .orElseThrow(() -> new NoSuchElementException("Matricula não encontrada com o ID: " + matriculaId));

	        if (usuarioIds != null && !usuarioIds.isEmpty()) {
	            // Remove os usuários da matrícula
	            matricula.getUsuarios().removeIf(usuario -> usuarioIds.contains(usuario.getId()));
	            matriculasRepository.save(matricula);
	        }

	        return modelMapper.map(matricula, GetMatriculaDTO.class);
	    } catch (Exception ex) {
	        // Trate a exceção de forma apropriada, registre logs, etc.
	        throw new Exception("Erro ao excluir usuários da Matricula.", ex);
	    }
	}

}