package school.sptech.cursos.dto.area;

import school.sptech.cursos.entity.AreaCurso;

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
