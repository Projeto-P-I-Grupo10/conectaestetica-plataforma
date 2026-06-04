package school.sptech.cursos.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.cursos.dto.area.AreaCursoRequest;
import school.sptech.cursos.dto.area.AreaCursoResponse;
import school.sptech.cursos.entity.AreaCurso;
import school.sptech.cursos.repository.IAreaCursoRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class AreaCursoService {

    private final IAreaCursoRepository repository;

    public AreaCursoService(IAreaCursoRepository repository) {
        this.repository = repository;
    }

    public List<AreaCursoResponse> listarAreasCurso()
    {
        List<AreaCurso> areas = this.repository.findAll();
        List<AreaCursoResponse> areasResponse = new ArrayList<>();
        for (AreaCurso area : areas)
        {
            AreaCursoResponse areaCursoResponse = new AreaCursoResponse(area);

            areaCursoResponse.setNome(area.getNome());
            areaCursoResponse.setId(area.getId());
            areasResponse.add(areaCursoResponse);
        }
        return areasResponse;
    }


    public AreaCurso buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id:"+ id +" não encontrado: "));
    }

    public AreaCursoResponse adicionarAreaCurso(AreaCursoRequest request)
    {
        AreaCurso areaCurso = new AreaCurso();
        areaCurso.setNome(request.getNome());
        areaCurso = this.repository.save(areaCurso);
        AreaCursoResponse response = new AreaCursoResponse(areaCurso);
        response.setNome(areaCurso.getNome());
        response.setId(areaCurso.getId());
        return response;
    }

    public void removerAreaCurso(Long id)
    {
        if (!this.repository.existsById(id)) {
            throw new EntityNotFoundException("Area do Curso não encontrado com id: " + id);
        }
        this.repository.deleteById(id);
    }

    public AreaCursoResponse atualizarAreaCurso(AreaCursoRequest request,Long id)
    {
        AreaCurso areaCurso = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Área do Curso não encontrada com id: " + id));
        areaCurso.setNome(request.getNome());
        areaCurso = this.repository.save(areaCurso);
        AreaCursoResponse response = new AreaCursoResponse(areaCurso);
        response.setId(areaCurso.getId());
        response.setNome(areaCurso.getNome());
        return response;
    }
}
