package school.sptech.cursos.dto.matricula;

import jakarta.validation.constraints.NotNull;
import school.sptech.cursos.enums.MatriculaEnum;

public class AtualizarStatusMatriculaRequest {

    @NotNull(message = "Status da matricula não pode ser nulo")
    private MatriculaEnum status;

    public MatriculaEnum getStatus() {
        return status;
    }

    public void setStatus(MatriculaEnum status) {
        this.status = status;
    }
}
