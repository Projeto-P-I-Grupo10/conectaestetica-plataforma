package school.sptech.cursos.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.cursos.DTO.Area.AreaCursoResponse;
import school.sptech.cursos.DTO.Curso.CursoRequest;
import school.sptech.cursos.DTO.Curso.CursoResponse;
import school.sptech.cursos.DTO.Professor.ProfessorResponse;
import school.sptech.cursos.model.AreaCurso;
import school.sptech.cursos.model.Curso;
import school.sptech.cursos.model.Professor;
import school.sptech.cursos.repository.IAreaCursoRepository;
import school.sptech.cursos.repository.ICursoRepository;
import school.sptech.cursos.repository.IProfessorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {
    private final ICursoRepository repository;
    private final IAreaCursoRepository repositoryArea;
    private final IProfessorRepository repositoryProfessor;
    public CursoService(ICursoRepository repository, IAreaCursoRepository repositoryArea, IProfessorRepository repositoryProfessor) {
        this.repository = repository;
        this.repositoryArea = repositoryArea;
        this.repositoryProfessor = repositoryProfessor;
    }

    public List<CursoResponse> listarCursos()
    {
        List<Curso> cursos = this.repository.findAll();
        List<CursoResponse> cursosResponse = new ArrayList<>();

        for (Curso curso : cursos)
        {
            cursosResponse.add(new CursoResponse(curso));
        }
        return cursosResponse;
    }

    public CursoResponse buscarPorId(Long id)
    {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com id: " + id));

        return new CursoResponse(curso);
    }

    public CursoResponse adicionarCurso(CursoRequest request)
    {
        AreaCurso area = this.repositoryArea.findById(request.getAreaCursoId())
                .orElseThrow(() -> new EntityNotFoundException("id da Área não encontrada"));

        Professor professor = this.repositoryProfessor.findById(request.getProfessorId())
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        Curso curso = new Curso();
        curso.setNome(request.getNome());
        curso.setDescricao(request.getDescricao());
        curso.setProfessor(professor);
        curso.setImagem(request.getImagem());
        curso.setArea(area);

        curso = this.repository.save(curso);

        return new CursoResponse(curso);
    }

    public CursoResponse atualizarCurso(CursoRequest request, Long id)
    {

        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com id: " + id));

        AreaCurso area = this.repositoryArea.findById(request.getAreaCursoId())
                .orElseThrow(() -> new EntityNotFoundException("id da área não encontrada"));

        Professor professor = this.repositoryProfessor.findById(request.getProfessorId())
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        curso.setNome(request.getNome());
        curso.setDescricao(request.getDescricao());
        curso.setImagem(request.getImagem());
        curso.setProfessor(professor);
        curso.setArea(area);

        curso = this.repository.save(curso);

        return new CursoResponse(curso);
    }


    public void removerCurso(Long id)
    {
        if (!this.repository.existsById(id)) {
            throw new EntityNotFoundException("Curso não encontrado com id: " + id);
        }
        this.repository.deleteById(id);
    }
}
