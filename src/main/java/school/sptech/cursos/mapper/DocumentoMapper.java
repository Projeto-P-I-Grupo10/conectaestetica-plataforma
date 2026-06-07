package school.sptech.cursos.mapper;

import org.springframework.stereotype.Component;
import school.sptech.cursos.dto.documento.DocumentoRequest;
import school.sptech.cursos.dto.documento.DocumentoResponse;
import school.sptech.cursos.entity.Documento;

@Component
public class DocumentoMapper implements IMapper<Documento, DocumentoRequest, DocumentoResponse>{

    @Override
    public Documento toEntity(DocumentoRequest request) {

        Documento documento = new Documento();
        documento.setArquivo(request.getArquivo());

        return documento;
    }

    @Override
    public DocumentoResponse toResponse(Documento documento) {

        DocumentoResponse response = new DocumentoResponse();

        response.setId(documento.getId());
        response.setArquivo(documento.getArquivo());

        if (documento.getMatricula() != null) {
            response.setMatriculaId(
                    documento.getMatricula().getId()
            );
        }

        return response;
    }
}
