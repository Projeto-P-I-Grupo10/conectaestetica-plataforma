package school.sptech.cursos.DTO;

import jakarta.validation.constraints.NotBlank;

public class AreaCursoRequest {
    @NotBlank(message = "Nome é Obrigatório")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
