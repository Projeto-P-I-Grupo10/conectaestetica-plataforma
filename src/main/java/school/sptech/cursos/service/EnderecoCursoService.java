package school.sptech.cursos.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.cursos.dto.enderecoCurso.EnderecoCursoRequest;
import school.sptech.cursos.dto.enderecoCurso.EnderecoCursoResponse;
import school.sptech.cursos.entity.EnderecoCurso;
import school.sptech.cursos.repository.IEnderecoCursoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoCursoService {

    private final IEnderecoCursoRepository repository;

    public EnderecoCursoService(IEnderecoCursoRepository repository) {
        this.repository = repository;
    }

    public List<EnderecoCursoResponse> listar() {
        List<EnderecoCurso> enderecos = repository.findAll();
        List<EnderecoCursoResponse> resposta = new ArrayList<>();

        for (EnderecoCurso endereco : enderecos) {
            resposta.add(new EnderecoCursoResponse(endereco));
        }

        return resposta;
    }

    public EnderecoCursoResponse salvar(EnderecoCursoRequest request) {
        EnderecoCurso endereco = new EnderecoCurso();
        endereco.setCep(request.getCep());
        endereco.setRua(request.getRua());
        endereco.setBairro(request.getBairro());
        endereco.setNumero(request.getNumero());
        endereco.setComplemento(request.getComplemento());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());

        endereco = repository.save(endereco);
        return new EnderecoCursoResponse(endereco);
    }

    public EnderecoCursoResponse atualizar(Long id, EnderecoCursoRequest request) {
        EnderecoCurso endereco = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));

        endereco.setCep(request.getCep());
        endereco.setRua(request.getRua());
        endereco.setBairro(request.getBairro());
        endereco.setNumero(request.getNumero());
        endereco.setComplemento(request.getComplemento());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());

        endereco = repository.save(endereco);

        return new EnderecoCursoResponse(endereco);
    }

    public void deletarPorId(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado");
        }
        repository.deleteById(id);
    }

    public EnderecoCursoResponse buscarPorId(Long id) {
        EnderecoCurso endereco = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
        return new EnderecoCursoResponse(endereco);
    }

}
