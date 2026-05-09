package school.sptech.cursos.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.cursos.DTO.Turma.TurmaRequest;
import school.sptech.cursos.DTO.Turma.TurmaResponse;
import school.sptech.cursos.model.Curso;
import school.sptech.cursos.model.EnderecoCurso;
import school.sptech.cursos.model.Turma;
import school.sptech.cursos.projection.TurmaDetalhesProjection;
import school.sptech.cursos.repository.ICursoRepository;
import school.sptech.cursos.repository.IEnderecoCursoRepository;
import school.sptech.cursos.repository.ITurmaRepository;

@Service
public class TurmaService {

    private final ITurmaRepository turmaRepository;
    private final ICursoRepository cursoRepository;
    private final IEnderecoCursoRepository enderecoCursoRepository;

    public TurmaService(ITurmaRepository turmaRepository, ICursoRepository cursoRepository, IEnderecoCursoRepository enderecoCursoRepository) {
        this.turmaRepository = turmaRepository;
        this.cursoRepository = cursoRepository;
        this.enderecoCursoRepository = enderecoCursoRepository;
    }

    public TurmaDetalhesProjection buscarDetalhes(Long id) {
        cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id:"+ id +" não encontrado na tabela curso: "));
        return turmaRepository.buscarDetalhesCurso(id)
                .orElseThrow(() -> new EntityNotFoundException("Id:"+ id +" não encontrado na tabela turma: "));
    }

    public TurmaResponse adicionarTurma(TurmaRequest request)
    {
        EnderecoCurso enderecoCurso = enderecoCursoRepository.findById(request.getEnderecoId())
                .orElseThrow(() -> new EntityNotFoundException("Id:"+ request.getEnderecoId() +" não encontrado na tabela endereço curso: "));
        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Id:"+ request.getEnderecoId() +" não encontrado na tabela curso: "));

        Turma turma = new Turma();
        turma.setNome(request.getNome());
        turma.setDataInicio(request.getDataInicio());
        turma.setDataEncerramento(request.getDataEncerramento());
        turma.setPreco(request.getPreco());
        turma.setQtdVagas(request.getQtdVagas());
        turma.setCursoAtivo(request.getCursoAtivo());
        turma.setEndereco(enderecoCurso);
        turma.setCurso(curso);
        turma = turmaRepository.save(turma);
        return new TurmaResponse(turma);
    }

    public void removerTurma(Long id)
    {
        if (!this.turmaRepository.existsById(id)) {
            throw new EntityNotFoundException("Turma não encontrado com id: " + id);
        }
        this.turmaRepository.deleteById(id);
    }

    public TurmaResponse atualizarTurma(TurmaRequest request, Long id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com id: " + id));

        EnderecoCurso enderecoCurso = enderecoCursoRepository.findById(request.getEnderecoId())
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com id: " + request.getEnderecoId()));

        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com id: " + request.getCursoId()));

        turma.setNome(request.getNome());
        turma.setDataInicio(request.getDataInicio());
        turma.setDataEncerramento(request.getDataEncerramento());
        turma.setPreco(request.getPreco());
        turma.setQtdVagas(request.getQtdVagas());
        turma.setCursoAtivo(request.getCursoAtivo());
        turma.setEndereco(enderecoCurso);
        turma.setCurso(curso);
        Turma turmaAtualizada = turmaRepository.save(turma);

        return new TurmaResponse(turmaAtualizada);
    }

}
