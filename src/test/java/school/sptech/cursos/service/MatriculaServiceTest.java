package school.sptech.cursos.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.cursos.dto.matricula.AtualizarStatusMatriculaRequest;
import school.sptech.cursos.dto.matricula.MatriculaRequest;
import school.sptech.cursos.dto.matricula.MatriculaResponse;
import school.sptech.cursos.entity.Matricula;
import school.sptech.cursos.entity.Turma;
import school.sptech.cursos.entity.Usuario;
import school.sptech.cursos.enums.MatriculaEnum;
import school.sptech.cursos.mapper.DocumentoMapper;
import school.sptech.cursos.mapper.MatriculaMapper;
import school.sptech.cursos.repository.IMatriculaRepository;
import school.sptech.cursos.repository.ITurmaRepository;
import school.sptech.cursos.repository.IUsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("MatriculaService")
@ExtendWith(MockitoExtension.class)
class MatriculaServiceTest {

    @Mock
    private IMatriculaRepository matriculaRepository;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @Mock
    private ITurmaRepository turmaRepository;

    @Mock
    private MatriculaMapper matriculaMapper;

    @Mock
    private DocumentoMapper documentoMapper;

    @InjectMocks
    private MatriculaService service;

    private Usuario criarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        return usuario;
    }

    private Turma criarTurma() {
        Turma turma = new Turma();
        turma.setId(1L);
        return turma;
    }

    private Matricula criarMatricula() {
        Matricula matricula = new Matricula();
        matricula.setDocumentos(new ArrayList<>());
        matricula.setUsuario(criarUsuario());
        matricula.setTurma(criarTurma());

        return matricula;
    }

    private MatriculaResponse criarResponse() {
        MatriculaResponse response = new MatriculaResponse();
        response.setId(1L);

        return response;
    }

    @Test
    @DisplayName("Deve listar todas as matrículas")
    void deveListarMatriculas() {

        // Arrange
        Matricula matricula = criarMatricula();

        MatriculaResponse response = criarResponse();

        when(matriculaRepository.findAll())
                .thenReturn(List.of(matricula));

        when(matriculaMapper.toResponse(matricula))
                .thenReturn(response);

        // Act
        List<MatriculaResponse> resultado = service.listar();

        // Assert
        assertEquals(1, resultado.size());

        verify(matriculaRepository).findAll();
        verify(matriculaMapper).toResponse(matricula);
    }

    @Test
    @DisplayName("Deve buscar matrícula por id")
    void deveBuscarMatriculaPorId() {

        // Arrange
        Matricula matricula = criarMatricula();

        MatriculaResponse response = criarResponse();

        when(matriculaRepository.findById(1L))
                .thenReturn(Optional.of(matricula));

        when(matriculaMapper.toResponse(matricula))
                .thenReturn(response);

        // Act
        MatriculaResponse resultado =
                service.buscarPorId(1L);

        // Assert
        assertEquals(1L, resultado.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar matrícula inexistente")
    void deveLancarExcecaoAoBuscarMatriculaInexistente() {

        // Arrange
        when(matriculaRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                RuntimeException.class,
                () -> service.buscarPorId(1L)
        );
    }

    @Test
    @DisplayName("Deve criar matrícula com sucesso")
    void deveCriarMatricula() {

        // Arrange
        MatriculaRequest request = new MatriculaRequest();
        request.setUsuarioId(1L);
        request.setTurmaId(1L);

        Usuario usuario = criarUsuario();
        Turma turma = criarTurma();

        Matricula matricula = criarMatricula();

        MatriculaResponse response = criarResponse();

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(turmaRepository.findById(1L))
                .thenReturn(Optional.of(turma));

        when(matriculaMapper.toEntity(request))
                .thenReturn(matricula);

        when(matriculaRepository.save(any()))
                .thenReturn(matricula);

        when(matriculaMapper.toResponse(matricula))
                .thenReturn(response);

        // Act
        MatriculaResponse resultado =
                service.criar(request);

        // Assert
        assertEquals(1L, resultado.getId());

        verify(matriculaRepository).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar matrícula com usuário inexistente")
    void deveLancarExcecaoAoCriarSemUsuario() {

        // Arrange
        MatriculaRequest request = new MatriculaRequest();
        request.setUsuarioId(1L);

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                RuntimeException.class,
                () -> service.criar(request)
        );
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar matrícula com turma inexistente")
    void deveLancarExcecaoAoCriarSemTurma() {

        // Arrange
        MatriculaRequest request = new MatriculaRequest();
        request.setUsuarioId(1L);
        request.setTurmaId(1L);

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(criarUsuario()));

        when(turmaRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                RuntimeException.class,
                () -> service.criar(request)
        );
    }

    @Test
    @DisplayName("Deve atualizar matrícula com sucesso")
    void deveAtualizarMatricula() {

        // Arrange
        Matricula matricula = criarMatricula();

        MatriculaRequest request = new MatriculaRequest();
        request.setUsuarioId(1L);
        request.setTurmaId(1L);

        MatriculaResponse response = criarResponse();

        when(matriculaRepository.findById(1L))
                .thenReturn(Optional.of(matricula));

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(criarUsuario()));

        when(turmaRepository.findById(1L))
                .thenReturn(Optional.of(criarTurma()));

        when(matriculaRepository.save(any()))
                .thenReturn(matricula);

        when(matriculaMapper.toResponse(matricula))
                .thenReturn(response);

        // Act
        MatriculaResponse resultado =
                service.atualizar(1L, request);

        // Assert
        assertEquals(1L, resultado.getId());
    }

    @Test
    @DisplayName("Deve atualizar status para ACEITA")
    void deveAtualizarStatusParaAceita() {

        // Arrange
        Matricula matricula = criarMatricula();

        AtualizarStatusMatriculaRequest request =
                new AtualizarStatusMatriculaRequest();

        request.setStatus(MatriculaEnum.ACEITA);

        MatriculaResponse response = criarResponse();

        when(matriculaRepository.findById(1L))
                .thenReturn(Optional.of(matricula));

        when(matriculaRepository.save(any()))
                .thenReturn(matricula);

        when(matriculaMapper.toResponse(matricula))
                .thenReturn(response);

        // Act
        MatriculaResponse resultado =
                service.atualizarStatus(1L, request);

        // Assert
        assertNotNull(resultado);

        assertEquals(
                MatriculaEnum.ACEITA,
                matricula.getStatus()
        );

        assertNotNull(matricula.getDataAprovacao());
    }

    @Test
    @DisplayName("Deve lançar exceção quando matrícula estiver rejeitada")
    void deveLancarExcecaoQuandoMatriculaRejeitada() {

        // Arrange
        Matricula matricula = criarMatricula();
        matricula.setStatus(MatriculaEnum.REJEITADA);

        AtualizarStatusMatriculaRequest request =
                new AtualizarStatusMatriculaRequest();

        request.setStatus(MatriculaEnum.ACEITA);

        when(matriculaRepository.findById(1L))
                .thenReturn(Optional.of(matricula));

        // Act & Assert
        assertThrows(
                ResponseStatusException.class,
                () -> service.atualizarStatus(1L, request)
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando status já estiver definido")
    void deveLancarExcecaoQuandoStatusJaAlterado() {

        // Arrange
        Matricula matricula = criarMatricula();
        matricula.setStatus(MatriculaEnum.ACEITA);

        AtualizarStatusMatriculaRequest request =
                new AtualizarStatusMatriculaRequest();

        request.setStatus(MatriculaEnum.ACEITA);

        when(matriculaRepository.findById(1L))
                .thenReturn(Optional.of(matricula));

        // Act & Assert
        assertThrows(
                ResponseStatusException.class,
                () -> service.atualizarStatus(1L, request)
        );
    }

    @Test
    @DisplayName("Deve deletar matrícula existente")
    void deveDeletarMatricula() {

        // Arrange
        Matricula matricula = criarMatricula();

        when(matriculaRepository.findById(1L))
                .thenReturn(Optional.of(matricula));

        // Act
        service.deletar(1L);

        // Assert
        verify(matriculaRepository)
                .delete(matricula);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar matrícula inexistente")
    void deveLancarExcecaoAoDeletarMatriculaInexistente() {

        // Arrange
        when(matriculaRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                RuntimeException.class,
                () -> service.deletar(1L)
        );
    }
}