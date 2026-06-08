package school.sptech.cursos.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import school.sptech.cursos.dto.documento.DocumentoRequest;
import school.sptech.cursos.dto.documento.DocumentoResponse;
import school.sptech.cursos.entity.Documento;
import school.sptech.cursos.entity.Matricula;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DocumentoMapper")
class DocumentoMapperTest {

    private final DocumentoMapper mapper = new DocumentoMapper();

    @Test
    @DisplayName("Deve converter DocumentoRequest para Documento")
    void deveConverterRequestParaEntity() {

        // Arrange
        DocumentoRequest request = new DocumentoRequest();
        request.setArquivo("arquivo.pdf");

        // Act
        Documento documento = mapper.toEntity(request);

        // Assert
        assertNotNull(documento);
        assertEquals("arquivo.pdf", documento.getArquivo());
    }

    @Test
    @DisplayName("Deve converter Documento para DocumentoResponse com matrícula")
    void deveConverterEntityParaResponseComMatricula() {

        // Arrange
        Matricula matricula = new Matricula();

        Documento documento = new Documento();
        documento.setArquivo("arquivo.pdf");
        documento.setMatricula(matricula);

        // Act
        DocumentoResponse response = mapper.toResponse(documento);

        // Assert
        assertNotNull(response);
        assertEquals("arquivo.pdf", response.getArquivo());
    }

    @Test
    @DisplayName("Deve converter Documento para DocumentoResponse sem matrícula")
    void deveConverterEntityParaResponseSemMatricula() {

        // Arrange
        Documento documento = new Documento();
        documento.setArquivo("arquivo.pdf");

        // Act
        DocumentoResponse response = mapper.toResponse(documento);

        // Assert
        assertNotNull(response);
        assertEquals("arquivo.pdf", response.getArquivo());
    }
}