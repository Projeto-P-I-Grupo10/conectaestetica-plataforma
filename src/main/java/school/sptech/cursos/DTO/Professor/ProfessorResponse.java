package school.sptech.cursos.DTO.Professor;

import school.sptech.cursos.model.AreaCurso;
import school.sptech.cursos.model.Professor;

public class ProfessorResponse {

    private Long id;
    private String nome;
    private String foto;
    private String descricao;
    private String email;
    private String redesocial;

    public ProfessorResponse() {
    }

    public ProfessorResponse(Professor professor) {
        this.id = professor.getId();
        this.nome = professor.getNome();
        this.foto = professor.getFoto();
        this.descricao = professor.getDescricao();
        this.email = professor.getEmail();
        this.redesocial = professor.getRedesocial();
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRedesocial() {
        return redesocial;
    }

    public void setRedesocial(String redesocial) {
        this.redesocial = redesocial;
    }
}