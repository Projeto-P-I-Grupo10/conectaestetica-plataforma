package school.sptech.cursos.dto.avaliacaoCurso;

import school.sptech.cursos.entity.AvaliacaoCurso;

public class AvaliacaoCursoResponse {
    private Long idCurso;
    private Long idUsuario;
    private Double avaliacao;
    private String comentario;

    public AvaliacaoCursoResponse(AvaliacaoCurso avaliacao) {
        this.idCurso = avaliacao.getCurso().getId();
        this.idUsuario = avaliacao.getUsuario().getId();
        this.avaliacao = avaliacao.getAvaliacao();
        this.comentario = avaliacao.getComentario();
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
