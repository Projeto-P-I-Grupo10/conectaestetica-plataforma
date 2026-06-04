package school.sptech.cursos.mapper;

import school.sptech.cursos.dto.matricula.MatriculaRequest;
import school.sptech.cursos.dto.matricula.MatriculaResponse;
import school.sptech.cursos.entity.Matricula;

public class MatriculaMapper implements IMapper<Matricula, MatriculaRequest, MatriculaResponse>{
    @Override
    public Matricula toEntity(MatriculaRequest request) {

        Matricula matricula = new Matricula();
//        matricula.setUsuario();

        return null;
    }

    @Override
    public MatriculaResponse toResponse(Matricula matricula) {
        return new MatriculaResponse(
                matricula.getId(),
                matricula.getUsuario().getId(),
                matricula.getTurma().getId(),
                matricula.getDataSolicitacao(),
                matricula.getDataAprovacao(),
                matricula.getDocumentos(),
                matricula.getStatus());
    }
}
