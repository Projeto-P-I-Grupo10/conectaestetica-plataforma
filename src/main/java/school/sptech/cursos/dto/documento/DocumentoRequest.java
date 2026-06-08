package school.sptech.cursos.dto.documento;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DocumentoRequest {
    private Long matriculaId;

    @NotBlank(message = "Arquivo é obrigatório")
    private String arquivo;

    public Long getMatriculaId() {
        return matriculaId;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
