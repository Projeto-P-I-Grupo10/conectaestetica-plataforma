package school.sptech.cursos.DTO.Matricula;

import school.sptech.cursos.enums.MatriculaEnum;
import school.sptech.cursos.model.Documento;

import java.time.LocalDateTime;
import java.util.List;

public record MatriculaResponse(
        Long id,
        Long usuarioId,
        Long turmaId,
        LocalDateTime dataSolicitacao,
        LocalDateTime dataAprovacao,
        List<Documento> documentos,
        MatriculaEnum status) {
}
