package school.sptech.cursos.dto.curso;

import jakarta.validation.constraints.*;

public class CursoRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Descricao é obrigatória")
    private String descricao;

    @NotNull(message = "Imagem é obrigatória")
    private String imagem;

    @NotNull(message = "Área é obrigatória")
    private Long areaCursoId;

    @NotNull(message = "Professor é obrigatório")
    private Long professorId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Long getAreaCursoId() {
        return areaCursoId;
    }

    public void setAreaCursoId(Long areaCursoId) {
        this.areaCursoId = areaCursoId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }
}

