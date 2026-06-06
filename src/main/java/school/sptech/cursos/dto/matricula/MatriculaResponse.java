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
    private UsuarioResponse usuario;
    private TurmaResponse turma;
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

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponse usuario) {
        this.usuario = usuario;
    }

    public TurmaResponse getTurma() {
        return turma;
    }

    public void setTurma(TurmaResponse turma) {
        this.turma = turma;
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
