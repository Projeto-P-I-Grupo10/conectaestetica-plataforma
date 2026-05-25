package school.sptech.cursos.DTO.AvaliacaoCurso;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AvaliacaoCursoRequest {

    @NotNull(message = "Curso é obrigatório")
    private Long cursoId;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "A avaliação é obrigatória")
    @DecimalMin(value = "0.0", message = "A nota mínima é 0")
    @DecimalMax(value = "5.0", message = "A nota máxima é 5")
    private Double avaliacao;

    @Size(max = 500, message = "O comentário deve ter no máximo 500 caracteres")
    private String comentario;


    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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