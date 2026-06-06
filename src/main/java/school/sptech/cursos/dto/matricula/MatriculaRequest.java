package school.sptech.cursos.dto.matricula;

import jakarta.validation.constraints.NotNull;
import school.sptech.cursos.dto.documento.DocumentoRequest;
import school.sptech.cursos.dto.documento.DocumentoResponse;
import school.sptech.cursos.enums.MatriculaEnum;
import school.sptech.cursos.entity.Documento;

import java.time.LocalDateTime;
import java.util.List;

public class MatriculaRequest {
    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "Turma é obrigatório")
    private Long turmaId;

    @NotNull(message = "Data de solicitacao é obrigatória")
    private LocalDateTime dataSolicitacao;

    @NotNull(message = "Data de aprovacao é obrigatória")
    private LocalDateTime dataAprovacao;

    @NotNull(message = "Lista de documentos é obrigatória")
    private List<DocumentoRequest> documentos;

    @NotNull(message = "Status é obrigatório")
    private MatriculaEnum status;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public LocalDateTime getDataAprovacao() {
        return dataAprovacao;
    }

    public List<DocumentoRequest> getDocumentos() {
        return documentos;
    }

    public MatriculaEnum getStatus() {
        return status;
    }
}
