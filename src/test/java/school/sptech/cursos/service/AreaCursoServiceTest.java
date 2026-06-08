package school.sptech.cursos.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cursos.dto.area.AreaCursoRequest;
import school.sptech.cursos.dto.area.AreaCursoResponse;
import school.sptech.cursos.entity.AreaCurso;
import school.sptech.cursos.repository.IAreaCursoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("AreaCursoService")
@ExtendWith(MockitoExtension.class)
class AreaCursoServiceTest {

    @Mock
    private IAreaCursoRepository repository;

    @InjectMocks
    private AreaCursoService service;

    @Test
    @DisplayName("Deve listar todas as áreas de curso")
    void deveListarTodasAsAreasCurso() {

        // Arrange
        AreaCurso area1 = new AreaCurso();
        area1.setId(1L);
        area1.setNome("Estética Facial");

        AreaCurso area2 = new AreaCurso();
        area2.setId(2L);
        area2.setNome("Massoterapia");

        when(repository.findAll()).thenReturn(List.of(area1, area2));

        // Act
        List<AreaCursoResponse> resultado = service.listarAreasCurso();

        // Assert
        assertEquals(2, resultado.size());

        assertEquals(1L, resultado.get(0).getId());
        assertEquals("Estética Facial", resultado.get(0).getNome());

        assertEquals(2L, resultado.get(1).getId());
        assertEquals("Massoterapia", resultado.get(1).getNome());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Deve retornar área de curso quando o id existir")
    void deveBuscarAreaCursoPorId() {

        // Arrange
        AreaCurso area = new AreaCurso();
        area.setId(1L);
        area.setNome("Estética Facial");

        when(repository.findById(1L))
                .thenReturn(Optional.of(area));

        // Act
        AreaCurso resultado = service.buscarPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Estética Facial", resultado.getNome());

        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar área de curso inexistente")
    void deveLancarExcecaoAoBuscarAreaInexistente() {

        // Arrange
        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception =
                assertThrows(EntityNotFoundException.class,
                        () -> service.buscarPorId(1L));

        assertTrue(exception.getMessage().contains("Id:1"));

        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Deve adicionar uma nova área de curso")
    void deveAdicionarAreaCurso() {

        // Arrange
        AreaCursoRequest request = new AreaCursoRequest();
        request.setNome("Estética Corporal");

        AreaCurso areaSalva = new AreaCurso();
        areaSalva.setId(1L);
        areaSalva.setNome("Estética Corporal");

        when(repository.save(any(AreaCurso.class)))
                .thenReturn(areaSalva);

        // Act
        AreaCursoResponse resultado =
                service.adicionarAreaCurso(request);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Estética Corporal", resultado.getNome());

        verify(repository).save(any(AreaCurso.class));
    }

    @Test
    @DisplayName("Deve remover área de curso quando o id existir")
    void deveRemoverAreaCurso() {

        // Arrange
        when(repository.existsById(1L))
                .thenReturn(true);

        // Act
        service.removerAreaCurso(1L);

        // Assert
        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao remover área de curso inexistente")
    void deveLancarExcecaoAoRemoverAreaInexistente() {

        // Arrange
        when(repository.existsById(1L))
                .thenReturn(false);

        // Act & Assert
        EntityNotFoundException exception =
                assertThrows(EntityNotFoundException.class,
                        () -> service.removerAreaCurso(1L));

        assertTrue(exception.getMessage().contains("1"));

        verify(repository).existsById(1L);
        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Deve atualizar área de curso existente")
    void deveAtualizarAreaCurso() {

        // Arrange
        AreaCursoRequest request = new AreaCursoRequest();
        request.setNome("Nova Área");

        AreaCurso area = new AreaCurso();
        area.setId(1L);
        area.setNome("Área Antiga");

        when(repository.findById(1L))
                .thenReturn(Optional.of(area));

        when(repository.save(any(AreaCurso.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        AreaCursoResponse resultado =
                service.atualizarAreaCurso(request, 1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Nova Área", resultado.getNome());

        verify(repository).findById(1L);
        verify(repository).save(any(AreaCurso.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar área de curso inexistente")
    void deveLancarExcecaoAoAtualizarAreaInexistente() {

        // Arrange
        AreaCursoRequest request = new AreaCursoRequest();
        request.setNome("Nova Área");

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception =
                assertThrows(
                        EntityNotFoundException.class,
                        () -> service.atualizarAreaCurso(request, 1L)
                );

        assertTrue(exception.getMessage().contains("1"));

        verify(repository).findById(1L);
        verify(repository, never()).save(any());
    }
}
