package school.sptech.cursos.DTO.Matricula;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import school.sptech.cursos.enums.MatriculaEnum;
import school.sptech.cursos.model.Documento;

import java.time.LocalDateTime;
import java.util.List;

public class MatriculaRequest {
    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "Turma é obrigatório")
    private Long turmaId;

    @NotNull(message = "Data de solicitacao é obrigatória")
    private LocalDateTime dataSolicitacao;

    @NotNull(message = "Data de aprovacao é obrigatória")
    private LocalDateTime dataAprovacao;

    @NotNull(message = "Lista de documentos é obrigatória")
    private List<Documento> documentos;

    @NotNull(message = "Status é obrigatório")
    private MatriculaEnum status;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public LocalDateTime getDataAprovacao() {
        return dataAprovacao;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public MatriculaEnum getStatus() {
        return status;
    }
}
