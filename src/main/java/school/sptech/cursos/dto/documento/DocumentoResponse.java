package school.sptech.cursos.dto.documento;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import school.sptech.cursos.dto.matricula.MatriculaResponse;

@JsonPropertyOrder({
        "id",
        "matricula",
        "arquivo"
})
public class DocumentoResponse {
    private Long id;
    private Long matriculaId;
    private String arquivo;

    public DocumentoResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
