package school.sptech.cursos.service;

import school.sptech.cursos.DTO.Matricula.MatriculaResponse;
import school.sptech.cursos.model.Documento;
import school.sptech.cursos.model.Matricula;
import school.sptech.cursos.repository.IMatriculaRepository;
import school.sptech.cursos.repository.ITurmaRepository;
import school.sptech.cursos.repository.IUsuarioRepository;

import java.util.ArrayList;
import java.util.List;

public class MatriculaService {
    private final IMatriculaRepository matriculaRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ITurmaRepository turmaRepository;

    public MatriculaService(IMatriculaRepository matriculaRepository, IUsuarioRepository usuarioRepository, ITurmaRepository turmaRepository) {
        this.matriculaRepository = matriculaRepository;
        this.usuarioRepository = usuarioRepository;
        this.turmaRepository = turmaRepository;
    }

    public List<MatriculaResponse> listar() {
        List<Matricula> matriculas = matriculaRepository.findAll();

        List<MatriculaResponse> resposta = new ArrayList<>();

        for (Matricula matricula : matriculas) {
            MatriculaResponse matriculaResponse = new MatriculaResponse(
                    matricula.getId(),
                    matricula.getUsuario().getId(),
                    matricula.getTurma().getId(),
                    matricula.getDataSolicitacao(),
                    matricula.getDataAprovacao(),
                    matricula.getDocumentos(),
                    matricula.getStatus());

            resposta.add(matriculaResponse);
        }
        return resposta;
    }

    public MatriculaResponse buscarPorId(Long id) {
        return null;
    }
}
