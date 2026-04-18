package school.sptech.cursos.DTO.Professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ProfessorRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Foto é obrigatório")
    private String foto;
    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;
    @Email
    private String email;
    private String redesocial;

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