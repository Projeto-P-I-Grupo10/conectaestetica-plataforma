package school.sptech.cursos.dto.matricula;

import school.sptech.cursos.enums.MatriculaEnum;
import school.sptech.cursos.entity.Documento;

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
