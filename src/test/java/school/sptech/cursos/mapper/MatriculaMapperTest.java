package school.sptech.cursos.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cursos.dto.documento.DocumentoRequest;
import school.sptech.cursos.dto.documento.DocumentoResponse;
import school.sptech.cursos.dto.matricula.MatriculaRequest;
import school.sptech.cursos.dto.matricula.MatriculaResponse;
import school.sptech.cursos.entity.Documento;
import school.sptech.cursos.entity.Matricula;
import school.sptech.cursos.entity.Turma;
import school.sptech.cursos.entity.Usuario;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("MatriculaMapper")
@ExtendWith(MockitoExtension.class)
class MatriculaMapperTest {

    @Mock
    private DocumentoMapper documentoMapper;

    @InjectMocks
    private MatriculaMapper mapper;

    @Test
    @DisplayName("Deve converter MatriculaRequest para Matricula com documentos")
    void deveConverterRequestParaEntityComDocumentos() {

        // Arrange
        DocumentoRequest documentoRequest = new DocumentoRequest();
        documentoRequest.setArquivo("arquivo.pdf");

        MatriculaRequest request = new MatriculaRequest();
        request.setDocumentos(List.of(documentoRequest));

        // Act
        Matricula matricula = mapper.toEntity(request);

        // Assert
        assertNotNull(matricula);
        assertNotNull(matricula.getDocumentos());
        assertEquals(1, matricula.getDocumentos().size());

        Documento documento = matricula.getDocumentos().getFirst();

        assertEquals("arquivo.pdf", documento.getArquivo());
        assertSame(matricula, documento.getMatricula());
    }

    @Test
    @DisplayName("Deve converter MatriculaRequest para Matricula sem documentos")
    void deveConverterRequestParaEntitySemDocumentos() {

        // Arrange
        MatriculaRequest request = new MatriculaRequest();

        // Act
        Matricula matricula = mapper.toEntity(request);

        // Assert
        assertNotNull(matricula);
        assertNull(matricula.getDocumentos());
    }

    @Test
    @DisplayName("Deve converter Matricula para MatriculaResponse com documentos")
    void deveConverterEntityParaResponseComDocumentos() {

        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Turma turma = new Turma();
        turma.setId(2L);

        Documento documento = new Documento();

        DocumentoResponse documentoResponse = new DocumentoResponse();
        documentoResponse.setId(3L);

        when(documentoMapper.toResponse(documento))
                .thenReturn(documentoResponse);

        Matricula matricula = new Matricula();
        matricula.setUsuario(usuario);
        matricula.setTurma(turma);
        matricula.setDataSolicitacao(LocalDateTime.now());
        matricula.setDataAprovacao(LocalDateTime.now());
        matricula.setDocumentos(List.of(documento));

        // Act
        MatriculaResponse response = mapper.toResponse(matricula);

        // Assert
        assertNotNull(response);

        assertNotNull(response.getDocumentos());
        assertEquals(1, response.getDocumentos().size());

        verify(documentoMapper).toResponse(documento);
    }

    @Test
    @DisplayName("Deve converter Matricula para MatriculaResponse sem documentos")
    void deveConverterEntityParaResponseSemDocumentos() {

        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Turma turma = new Turma();
        turma.setId(2L);

        Matricula matricula = new Matricula();
        matricula.setUsuario(usuario);
        matricula.setTurma(turma);
        matricula.setDocumentos(null);

        // Act
        MatriculaResponse response = mapper.toResponse(matricula);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getUsuarioId());
        assertEquals(2L, response.getTurmaId());

        assertNotNull(response.getDocumentos());
        assertTrue(response.getDocumentos().isEmpty());

        verifyNoInteractions(documentoMapper);
    }
}