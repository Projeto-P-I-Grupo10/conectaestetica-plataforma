package school.sptech.cursos.DTO.Curso;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import school.sptech.cursos.DTO.Area.AreaCursoResponse;
import school.sptech.cursos.DTO.EnderecoCurso.EnderecoCursoResponse;
import school.sptech.cursos.DTO.Professor.ProfessorResponse;
import school.sptech.cursos.model.Curso;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({
        "id",
        "nome",
        "descricao",
        "imagem",
        "area",
        "professor"
})
public class CursoResponse {

    private Long id;
    private String nome;
    private String descricao;
    private String imagem;
    private AreaCursoResponse area;
    private ProfessorResponse professor;

    public CursoResponse(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.descricao = curso.getDescricao();
        this.imagem = curso.getImagem();
        this.area = new AreaCursoResponse(curso.getArea());
        this.professor = new ProfessorResponse(curso.getProfessor());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ProfessorResponse getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorResponse professor) {
        this.professor = professor;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public AreaCursoResponse getArea() {
        return area;
    }

    public void setArea(AreaCursoResponse area) {
        this.area = area;
    }
}
