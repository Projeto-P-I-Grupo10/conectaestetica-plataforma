package school.sptech.cursos.dto.avaliacaoCurso;

import school.sptech.cursos.dto.usuario.UsuarioResponse;
import school.sptech.cursos.entity.AvaliacaoCurso;
import school.sptech.cursos.entity.Usuario;

public class AvaliacaoCursoResponse {
    private Long idCurso;
    private Double avaliacao;
    private String comentario;
    private UsuarioResponse usuario;

    public AvaliacaoCursoResponse(AvaliacaoCurso avaliacao) {
        this.idCurso = avaliacao.getCurso().getId();
        this.avaliacao = avaliacao.getAvaliacao();
        this.comentario = avaliacao.getComentario();
        this.usuario = new UsuarioResponse(avaliacao.getUsuario());
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponse usuario) {
        this.usuario = usuario;
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
