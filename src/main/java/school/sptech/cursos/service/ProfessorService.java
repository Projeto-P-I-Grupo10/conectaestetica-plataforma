package school.sptech.cursos.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.cursos.DTO.Professor.ProfessorRequest;
import school.sptech.cursos.DTO.Professor.ProfessorResponse;
import school.sptech.cursos.model.Professor;
import school.sptech.cursos.repository.IProfessorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    private final IProfessorRepository repository;

    public ProfessorService(IProfessorRepository repository) {
        this.repository = repository;
    }

    public List<ProfessorResponse> listar() {
        List<Professor> professores = repository.findAll();
        List<ProfessorResponse> resposta = new ArrayList<>();

        for (Professor professor : professores) {
            ProfessorResponse response = new ProfessorResponse();
            response.setId(professor.getId());
            response.setNome(professor.getNome());
            response.setFoto(professor.getFoto());
            response.setDescricao(professor.getDescricao());
            response.setEmail(professor.getEmail());
            response.setRedesocial(professor.getRedesocial());
            resposta.add(response);
        }

        return resposta;
    }

    public ProfessorResponse salvar(ProfessorRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        Professor professor = new Professor();
        professor.setNome(request.getNome());
        professor.setFoto(request.getFoto());
        professor.setDescricao(request.getDescricao());
        professor.setEmail(request.getEmail());
        professor.setRedesocial(request.getRedesocial());

        professor = repository.save(professor);

        ProfessorResponse response = new ProfessorResponse();
        response.setId(professor.getId());
        response.setNome(professor.getNome());
        response.setFoto(professor.getFoto());
        response.setDescricao(professor.getDescricao());
        response.setEmail(professor.getEmail());
        response.setRedesocial(professor.getRedesocial());

        return response;
    }

    public ProfessorResponse atualizar(Long id, ProfessorRequest request) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));

        if (!professor.getEmail().equals(request.getEmail()) &&
                repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        professor.setNome(request.getNome());
        professor.setFoto(request.getFoto());
        professor.setDescricao(request.getDescricao());
        professor.setEmail(request.getEmail());
        professor.setRedesocial(request.getRedesocial());

        professor = repository.save(professor);

        ProfessorResponse response = new ProfessorResponse();
        response.setId(professor.getId());
        response.setNome(professor.getNome());
        response.setFoto(professor.getFoto());
        response.setDescricao(professor.getDescricao());
        response.setEmail(professor.getEmail());
        response.setRedesocial(professor.getRedesocial());

        return response;
    }

    public void deletarPorId(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado");
        }
        repository.deleteById(id);
    }
}
