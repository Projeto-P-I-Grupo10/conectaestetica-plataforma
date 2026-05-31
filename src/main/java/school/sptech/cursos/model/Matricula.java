package school.sptech.cursos.model;

import jakarta.persistence.*;
import school.sptech.cursos.enums.MatriculaEnum;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "turma_id", referencedColumnName = "id")
    private Turma turma;

    private LocalDateTime dataSolicitacao;

    private LocalDateTime dataAprovacao;

    private List<Documento> documentos;

    private MatriculaEnum status = MatriculaEnum.EM_ANALISE;

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
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

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public MatriculaEnum getStatus() {
        return status;
    }

    public void setStatus(MatriculaEnum status) {
        this.status = status;
    }
}
