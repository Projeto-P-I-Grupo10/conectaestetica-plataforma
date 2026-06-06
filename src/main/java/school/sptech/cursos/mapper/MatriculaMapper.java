package school.sptech.cursos.mapper;

import org.springframework.stereotype.Component;
import school.sptech.cursos.dto.documento.DocumentoRequest;
import school.sptech.cursos.dto.documento.DocumentoResponse;
import school.sptech.cursos.dto.matricula.MatriculaRequest;
import school.sptech.cursos.dto.matricula.MatriculaResponse;
import school.sptech.cursos.dto.turma.TurmaResponse;
import school.sptech.cursos.dto.usuario.UsuarioResponse;
import school.sptech.cursos.entity.Documento;
import school.sptech.cursos.entity.Matricula;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatriculaMapper implements IMapper<Matricula, MatriculaRequest, MatriculaResponse>{

    private final DocumentoMapper documentoMapper;

    public MatriculaMapper(DocumentoMapper documentoMapper) {
        this.documentoMapper = documentoMapper;
    }

    @Override
    public Matricula toEntity(MatriculaRequest req) {

        Matricula matricula = new Matricula();

        if (req.getDocumentos() != null) {

            List<Documento> documentos = new ArrayList<>();

            for (DocumentoRequest dto : req.getDocumentos()) {

                Documento documento = new Documento();
                documento.setArquivo(dto.getArquivo());
                documento.setMatricula(matricula);

                documentos.add(documento);
            }

            matricula.setDocumentos(documentos);
        }

        return matricula;
    }

    @Override
    public MatriculaResponse toResponse(Matricula matricula) {

        MatriculaResponse response = new MatriculaResponse();

        response.setId(matricula.getId());
        response.setDataSolicitacao(matricula.getDataSolicitacao());
        response.setDataAprovacao(matricula.getDataAprovacao());
        response.setStatus(matricula.getStatus());

        response.setUsuario(
                new UsuarioResponse(matricula.getUsuario())
        );

        response.setTurma(
                new TurmaResponse(matricula.getTurma())
        );

        List<DocumentoResponse> documentos = new ArrayList<>();

        if (matricula.getDocumentos() != null) {
            for (Documento documento : matricula.getDocumentos()) {
                documentos.add(
                        documentoMapper.toResponse(documento)
                );
            }
        }

        response.setDocumentos(documentos);

        return response;
    }
}
