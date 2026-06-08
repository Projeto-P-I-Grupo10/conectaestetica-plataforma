package school.sptech.cursos.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cursos.dto.curso.CursoRequest;
import school.sptech.cursos.dto.curso.CursoResponse;
import school.sptech.cursos.entity.AreaCurso;
import school.sptech.cursos.entity.Curso;
import school.sptech.cursos.entity.Professor;
import school.sptech.cursos.repository.IAreaCursoRepository;
import school.sptech.cursos.repository.ICursoRepository;
import school.sptech.cursos.repository.IProfessorRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("CursoService")
@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private ICursoRepository repository;

    @Mock
    private IAreaCursoRepository repositoryArea;

    @Mock
    private IProfessorRepository repositoryProfessor;

    @InjectMocks
    private CursoService service;

    @Test
    @DisplayName("Deve listar todos os cursos")
    void deveListarCursos() {

        // Arrange

        AreaCurso area = new AreaCurso();
        area.setId(1L);
        area.setNome("Estética");

        Professor professor = new Professor();
        professor.setId(1L);

        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Curso de Estética");
        curso.setArea(area);
        curso.setProfessor(professor);

        when(repository.findAll())
                .thenReturn(List.of(curso));

        // Act
        List<CursoResponse> response = service.listarCursos();

        // Assert
        assertEquals(1, response.size());
        assertEquals(1L, response.get(0).getId());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Deve buscar curso por id")
    void deveBuscarCursoPorId() {

        // Arrange
        AreaCurso area = new AreaCurso();
        area.setId(1L);
        area.setNome("Estética");

        Professor professor = new Professor();
        professor.setId(1L);

        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Curso de Estética");
        curso.setArea(area);
        curso.setProfessor(professor);

        when(repository.findById(1L))
                .thenReturn(Optional.of(curso));

        // Act
        CursoResponse response = service.buscarPorId(1L);

        // Assert
        assertEquals(1L, response.getId());

        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando curso não existir")
    void deveLancarExcecaoQuandoCursoNaoExistir() {

        // Arrange
        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                EntityNotFoundException.class,
                () -> service.buscarPorId(1L)
        );
    }

    @Test
    @DisplayName("Deve adicionar curso com sucesso")
    void deveAdicionarCurso() {

        // Arrange
        CursoRequest request = new CursoRequest();
        request.setNome("Curso");
        request.setDescricao("Descrição");
        request.setImagem("imagem.jpg");
        request.setAreaCursoId(1L);
        request.setProfessorId(1L);

        AreaCurso area = new AreaCurso();
        area.setId(1L);

        Professor professor = new Professor();
        professor.setId(1L);

        Curso cursoSalvo = new Curso();
        cursoSalvo.setId(100L);
        cursoSalvo.setNome("Curso");
        cursoSalvo.setArea(area);
        cursoSalvo.setProfessor(professor);

        when(repositoryArea.findById(1L))
                .thenReturn(Optional.of(area));

        when(repositoryProfessor.findById(1L))
                .thenReturn(Optional.of(professor));

        when(repository.save(any(Curso.class)))
                .thenReturn(cursoSalvo);

        // Act
        CursoResponse response = service.adicionarCurso(request);

        // Assert
        assertNotNull(response);
        assertEquals(100L, response.getId());

        verify(repository).save(any(Curso.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao adicionar curso com área inexistente")
    void deveLancarExcecaoQuandoAreaNaoExistir() {

        // Arrange
        CursoRequest request = new CursoRequest();
        request.setAreaCursoId(1L);

        when(repositoryArea.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                EntityNotFoundException.class,
                () -> service.adicionarCurso(request)
        );

        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao adicionar curso com professor inexistente")
    void deveLancarExcecaoQuandoProfessorNaoExistir() {

        // Arrange
        CursoRequest request = new CursoRequest();
        request.setAreaCursoId(1L);
        request.setProfessorId(1L);

        AreaCurso area = new AreaCurso();
        area.setId(1L);

        when(repositoryArea.findById(1L))
                .thenReturn(Optional.of(area));

        when(repositoryProfessor.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                EntityNotFoundException.class,
                () -> service.adicionarCurso(request)
        );

        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve atualizar curso com sucesso")
    void deveAtualizarCurso() {

        // Arrange
        CursoRequest request = new CursoRequest();
        request.setNome("Novo Curso");
        request.setDescricao("Nova descrição");
        request.setImagem("nova.jpg");
        request.setAreaCursoId(1L);
        request.setProfessorId(1L);

        Curso curso = new Curso();
        curso.setId(1L);

        AreaCurso area = new AreaCurso();
        area.setId(1L);

        Professor professor = new Professor();
        professor.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(curso));

        when(repositoryArea.findById(1L))
                .thenReturn(Optional.of(area));

        when(repositoryProfessor.findById(1L))
                .thenReturn(Optional.of(professor));

        when(repository.save(any(Curso.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        CursoResponse response =
                service.atualizarCurso(request, 1L);

        // Assert
        assertEquals("Novo Curso", response.getNome());

        verify(repository).save(curso);
    }

    @Test
    @DisplayName("Deve remover curso existente")
    void deveRemoverCurso() {

        // Arrange
        when(repository.existsById(1L))
                .thenReturn(true);

        // Act
        service.removerCurso(1L);

        // Assert
        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao remover curso inexistente")
    void deveLancarExcecaoAoRemoverCursoInexistente() {

        // Arrange
        when(repository.existsById(1L))
                .thenReturn(false);

        // Act & Assert
        assertThrows(
                EntityNotFoundException.class,
                () -> service.removerCurso(1L)
        );

        verify(repository, never()).deleteById(anyLong());
    }
}