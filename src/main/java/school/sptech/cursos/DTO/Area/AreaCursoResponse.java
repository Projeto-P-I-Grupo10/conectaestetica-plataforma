package school.sptech.cursos.DTO.Area;

import school.sptech.cursos.model.AreaCurso;

public class AreaCursoResponse {

    public AreaCursoResponse(AreaCurso areaCurso) {
        this.id = areaCurso.getId();
        this.nome = areaCurso.getNome();
    }
    private Long id;
    private String nome;

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
}
