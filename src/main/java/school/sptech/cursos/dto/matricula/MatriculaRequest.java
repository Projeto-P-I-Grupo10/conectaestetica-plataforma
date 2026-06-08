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

    private LocalDateTime dataSolicitacao;

    private LocalDateTime dataAprovacao;

    @NotNull(message = "Lista de documentos é obrigatória")
    private List<DocumentoRequest> documentos;

    private MatriculaEnum status;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
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

    public void setDocumentos(List<DocumentoRequest> documentos) {
        this.documentos = documentos;
    }

    public MatriculaEnum getStatus() {
        return status;
    }
}
