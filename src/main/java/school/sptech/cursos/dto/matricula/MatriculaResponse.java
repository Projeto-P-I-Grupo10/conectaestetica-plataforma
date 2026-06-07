package school.sptech.cursos.dto.matricula;

import school.sptech.cursos.dto.documento.DocumentoResponse;
import school.sptech.cursos.dto.turma.TurmaResponse;
import school.sptech.cursos.dto.usuario.UsuarioResponse;
import school.sptech.cursos.entity.Turma;
import school.sptech.cursos.entity.Usuario;
import school.sptech.cursos.enums.MatriculaEnum;
import school.sptech.cursos.entity.Documento;

import java.time.LocalDateTime;
import java.util.List;

public class MatriculaResponse {
    private Long id;
    private Long usuarioId;
    private Long turmaId;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataAprovacao;
    private List<DocumentoResponse> documentos;
    private MatriculaEnum status;

    public MatriculaResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDateTime getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(LocalDateTime dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public List<DocumentoResponse> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoResponse> documentos) {
        this.documentos = documentos;
    }

    public MatriculaEnum getStatus() {
        return status;
    }

    public void setStatus(MatriculaEnum status) {
        this.status = status;
    }
}
