package school.sptech.cursos.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.cursos.DTO.AvaliacaoCurso.AvaliacaoCursoRequest;
import school.sptech.cursos.DTO.AvaliacaoCurso.AvaliacaoCursoResponse;
import school.sptech.cursos.DTO.Curso.CursoResponse;
import school.sptech.cursos.model.AvaliacaoCurso;
import school.sptech.cursos.model.Curso;
import school.sptech.cursos.model.Usuario;
import school.sptech.cursos.repository.IAvaliacaoCurso;
import school.sptech.cursos.repository.ICursoRepository;
import school.sptech.cursos.repository.IUsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvaliacaoCursoService {

    private final ICursoRepository cursoRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IAvaliacaoCurso avaliacaoCursoRepository;

    public AvaliacaoCursoService(
            ICursoRepository cursoRepository,
            IUsuarioRepository usuarioRepository,
            IAvaliacaoCurso avaliacaoCursoRepository
    ) {
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
        this.avaliacaoCursoRepository = avaliacaoCursoRepository;
    }

    public List<AvaliacaoCursoResponse> listarAvaliacoes() {
        List<AvaliacaoCurso> avaliacoes = avaliacaoCursoRepository.findAll();
        List<AvaliacaoCursoResponse> avaliacoesResponse = new ArrayList<>();

        for (AvaliacaoCurso avaliacao : avaliacoes)
        {
            avaliacoesResponse.add(new AvaliacaoCursoResponse(avaliacao));
        }
        return avaliacoesResponse;
    }

    public AvaliacaoCursoResponse buscarPorId(Long id) {
        AvaliacaoCurso avaliacaoCurso = avaliacaoCursoRepository.findById(id)
                                        .orElseThrow(() ->
                                                new EntityNotFoundException("Avaliação não encontrada com id: " + id));
        return new AvaliacaoCursoResponse(avaliacaoCurso);
    }

    public AvaliacaoCursoResponse criarAvaliacao(AvaliacaoCursoRequest request) {

        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Curso não encontrado com id: "
                                        + request.getCursoId()
                        ));

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Usuário não encontrado com id: "
                                        + request.getUsuarioId()
                        ));

        boolean jaExiste =
                avaliacaoCursoRepository.existsByCursoIdAndUsuarioId(
                        request.getCursoId(),
                        request.getUsuarioId()
                );

        if (jaExiste) {
            throw new IllegalArgumentException(
                    "Usuário já avaliou este curso"
            );
        }

        AvaliacaoCurso avaliacao = new AvaliacaoCurso();

        avaliacao.setCurso(curso);
        avaliacao.setUsuario(usuario);
        avaliacao.setAvaliacao(request.getAvaliacao());
        avaliacao.setComentario(request.getComentario());
        avaliacao = avaliacaoCursoRepository.save(avaliacao);
        return new AvaliacaoCursoResponse(avaliacao);
    }

    public AvaliacaoCursoResponse atualizarAvaliacao(
            Long id,
            AvaliacaoCursoRequest request
    ) {

        AvaliacaoCurso avaliacao = avaliacaoCursoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Avaliação não encontrada com id: " + id
                        ));

        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Curso não encontrado com id: "
                                        + request.getCursoId()
                        ));

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Usuário não encontrado com id: "
                                        + request.getUsuarioId()
                        ));

        avaliacao.setCurso(curso);
        avaliacao.setUsuario(usuario);
        avaliacao.setAvaliacao(request.getAvaliacao());
        avaliacao.setComentario(request.getComentario());
        avaliacao = avaliacaoCursoRepository.save(avaliacao);
        return new AvaliacaoCursoResponse(avaliacao);
    }

    public void deletarAvaliacao(Long id) {

        if (!avaliacaoCursoRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Avaliação não encontrada com id: " + id
            );
        }

        avaliacaoCursoRepository.deleteById(id);
    }
}