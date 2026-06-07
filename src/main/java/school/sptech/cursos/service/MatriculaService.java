package school.sptech.cursos.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.cursos.dto.documento.DocumentoRequest;
import school.sptech.cursos.dto.matricula.AtualizarStatusMatriculaRequest;
import school.sptech.cursos.dto.matricula.MatriculaRequest;
import school.sptech.cursos.dto.matricula.MatriculaResponse;
import school.sptech.cursos.entity.Documento;
import school.sptech.cursos.entity.Matricula;
import school.sptech.cursos.entity.Turma;
import school.sptech.cursos.entity.Usuario;
import school.sptech.cursos.enums.MatriculaEnum;
import school.sptech.cursos.mapper.DocumentoMapper;
import school.sptech.cursos.mapper.MatriculaMapper;
import school.sptech.cursos.repository.IMatriculaRepository;
import school.sptech.cursos.repository.ITurmaRepository;
import school.sptech.cursos.repository.IUsuarioRepository;

import java.time.LocalDateTime;
import java.util.IllegalFormatCodePointException;
import java.util.List;

@Service
public class MatriculaService {
    private final IMatriculaRepository matriculaRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ITurmaRepository turmaRepository;

    private final MatriculaMapper matriculaMapper;
    private final DocumentoMapper documentoMapper;

    public MatriculaService(IMatriculaRepository matriculaRepository,
                            IUsuarioRepository usuarioRepository,
                            ITurmaRepository turmaRepository,
                            MatriculaMapper matriculaMapper,
                            DocumentoMapper documentoMapper) {
        this.matriculaRepository = matriculaRepository;
        this.usuarioRepository = usuarioRepository;
        this.turmaRepository = turmaRepository;
        this.matriculaMapper = matriculaMapper;
        this.documentoMapper = documentoMapper;
    }

    public List<MatriculaResponse> listar() {

        return matriculaRepository.findAll()
                .stream()
                .map(matriculaMapper::toResponse)
                .toList();
    }

    public MatriculaResponse buscarPorId(Long id) {

        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Matrícula não encontrada"));

        return matriculaMapper.toResponse(matricula);
    }

    @Transactional
    public MatriculaResponse criar(MatriculaRequest request) {

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        Turma turma = turmaRepository.findById(request.getTurmaId())
                .orElseThrow(() ->
                        new RuntimeException("Turma não encontrada"));

        Matricula matricula = matriculaMapper.toEntity(request);

        matricula.setUsuario(usuario);
        matricula.setTurma(turma);
        matricula.setDataSolicitacao(LocalDateTime.now());

        if (request.getDocumentos() != null) {

            List<Documento> documentos = request.getDocumentos()
                    .stream()
                    .map(documentoMapper::toEntity)
                    .toList();

            Matricula finalMatricula = matricula;
            documentos.forEach(
                    documento -> documento.setMatricula(finalMatricula)
            );

            matricula.setDocumentos(documentos);
        }

        matricula = matriculaRepository.save(matricula);

        return matriculaMapper.toResponse(matricula);
    }

    @Transactional
    public MatriculaResponse atualizar(
            Long id,
            MatriculaRequest request) {

        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Matrícula não encontrada"));

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        Turma turma = turmaRepository.findById(request.getTurmaId())
                .orElseThrow(() ->
                        new RuntimeException("Turma não encontrada"));

        matricula.setUsuario(usuario);
        matricula.setTurma(turma);

        matricula.getDocumentos().clear();

        if (request.getDocumentos() != null) {

            for (DocumentoRequest documentoRequest : request.getDocumentos()) {

                Documento documento =
                        documentoMapper.toEntity(documentoRequest);

                documento.setMatricula(matricula);

                matricula.getDocumentos().add(documento);
            }
        }

        matricula = matriculaRepository.save(matricula);

        return matriculaMapper.toResponse(matricula);
    }

    @Transactional
    public MatriculaResponse atualizarStatus(
            Long id,
            AtualizarStatusMatriculaRequest request) {

        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Matrícula não encontrada"));

        if (matricula.getStatus() == MatriculaEnum.REJEITADA) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Matrícula rejeitada! Status não pode ser alterado!");
        }

        if (matricula.getStatus() == request.getStatus()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Status da matrícula já alterado");
        }

        matricula.setStatus(request.getStatus());

        if (request.getStatus() == MatriculaEnum.ACEITA) {
            matricula.setDataAprovacao(LocalDateTime.now());
        }

        matricula = matriculaRepository.save(matricula);

        return matriculaMapper.toResponse(matricula);
    }

    @Transactional
    public void deletar(Long id) {

        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Matrícula não encontrada"));

        matriculaRepository.delete(matricula);
    }
}
