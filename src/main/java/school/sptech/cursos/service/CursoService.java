package school.sptech.cursos.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.cursos.DTO.*;
import school.sptech.cursos.model.AreaCurso;
import school.sptech.cursos.model.Curso;
import school.sptech.cursos.model.EnderecoCurso;
import school.sptech.cursos.repository.IAreaCursoRepository;
import school.sptech.cursos.repository.ICursoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {
    private final ICursoRepository repository;
    private final IAreaCursoRepository repositoryArea;

    public CursoService(ICursoRepository repository, IAreaCursoRepository repositoryArea) {
        this.repository = repository;
        this.repositoryArea = repositoryArea;
    }

    public List<CursoResponse> listarCursos()
    {
        List<Curso> cursos = this.repository.findAll();
        List<CursoResponse> cursosResponse = new ArrayList<>();

        for (Curso curso : cursos)
        {
            AreaCurso area = this.repositoryArea.findByNome(curso.getArea().getNome())
                    .orElseThrow(() -> new EntityNotFoundException("nome da Área não encontrada"));
            EnderecoCursoResponse enderecoResponse = new EnderecoCursoResponse(curso.getEndereco());
            AreaCursoResponse areaCursoResponse = new AreaCursoResponse(area);
            CursoResponse cursoResponse = new CursoResponse(curso);
            cursoResponse.setDescricao(curso.getDescricao());
            cursoResponse.setDataInicio(curso.getDataInicio());
            cursoResponse.setDataEncerramento(curso.getDataEncerramento());
            cursoResponse.setProfessor(curso.getProfessor());
            cursoResponse.setPreco(curso.getPreco());
            cursoResponse.setQtdVagas(curso.getQtdVagas());
            cursoResponse.setCursoIniciado(curso.getCursoIniciado());
            cursoResponse.setEndereco(enderecoResponse);
            cursoResponse.setArea(areaCursoResponse);
            cursosResponse.add(cursoResponse);
        }
        return cursosResponse;
    }

    public CursoResponse adicionarCurso(CursoRequest request) {
        AreaCurso area = this.repositoryArea.findByNome(request.getAreaCurso().getNome())
                .orElseThrow(() -> new EntityNotFoundException("nome da Área não encontrada"));

        EnderecoCurso endereco = new EnderecoCurso();
        endereco.setRua(request.getEndereco().getRua());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());
        endereco.setNumero(request.getEndereco().getNumero());
        endereco.setComplemento(request.getEndereco().getComplemento());
        endereco.setBairro(request.getEndereco().getBairro());

        Curso curso = new Curso();
        curso.setNome(request.getNome());
        curso.setDescricao(request.getDescricao());
        curso.setDataInicio(request.getDataInicio());
        curso.setDataEncerramento(request.getDataEncerramento());
        curso.setProfessor(request.getProfessor());
        curso.setPreco(request.getPreco());
        curso.setQtdVagas(request.getQtdVagas());
        curso.setCursoIniciado(request.getCursoIniciado());
        curso.setArea(area);
        curso.setEndereco(endereco);

        curso = this.repository.save(curso);

        EnderecoCursoResponse enderecoResponse = new EnderecoCursoResponse(curso.getEndereco());
        AreaCursoResponse areaCursoResponse = new AreaCursoResponse(area);
        CursoResponse cursoResponse = new CursoResponse(curso);
        cursoResponse.setNome(request.getNome());
        cursoResponse.setDescricao(request.getDescricao());
        cursoResponse.setDataInicio(request.getDataInicio());
        cursoResponse.setDataEncerramento(request.getDataEncerramento());
        cursoResponse.setProfessor(request.getProfessor());
        cursoResponse.setPreco(request.getPreco());
        cursoResponse.setQtdVagas(request.getQtdVagas());
        cursoResponse.setCursoIniciado(request.getCursoIniciado());
        cursoResponse.setArea(areaCursoResponse);
        cursoResponse.setEndereco(enderecoResponse);


        return cursoResponse;
    }

    public CursoResponse atualizarCurso(CursoRequest request, Long id) {
        // Busca o curso existente
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com id: " + id));

        // Busca a area pelo nome
        AreaCurso area = this.repositoryArea.findByNome(request.getAreaCurso().getNome())
                .orElseThrow(() -> new EntityNotFoundException("Nome da área não encontrada"));

        // Atualiza endereço
        EnderecoCurso endereco = new EnderecoCurso();
        endereco.setRua(request.getEndereco().getRua());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());
        endereco.setNumero(request.getEndereco().getNumero());
        endereco.setComplemento(request.getEndereco().getComplemento());
        endereco.setBairro(request.getEndereco().getBairro());

        // Atualiza os dados do curso existente
        curso.setNome(request.getNome());
        curso.setDescricao(request.getDescricao());
        curso.setDataInicio(request.getDataInicio());
        curso.setDataEncerramento(request.getDataEncerramento());
        curso.setProfessor(request.getProfessor());
        curso.setPreco(request.getPreco());
        curso.setQtdVagas(request.getQtdVagas());
        curso.setCursoIniciado(request.getCursoIniciado());
        curso.setArea(area);
        curso.setEndereco(endereco);

        // Salva as alterações
        curso = this.repository.save(curso);

        // Monta o response manualmente
        EnderecoCursoResponse enderecoResponse = new EnderecoCursoResponse(curso.getEndereco());
        AreaCursoResponse areaCursoResponse = new AreaCursoResponse(curso.getArea());

        CursoResponse cursoResponse = new CursoResponse(curso);
        cursoResponse.setId(curso.getId());
        cursoResponse.setNome(curso.getNome());
        cursoResponse.setDescricao(curso.getDescricao());
        cursoResponse.setDataInicio(curso.getDataInicio());
        cursoResponse.setDataEncerramento(curso.getDataEncerramento());
        cursoResponse.setProfessor(curso.getProfessor());
        cursoResponse.setPreco(curso.getPreco());
        cursoResponse.setQtdVagas(curso.getQtdVagas());
        cursoResponse.setCursoIniciado(curso.getCursoIniciado());
        cursoResponse.setArea(areaCursoResponse);
        cursoResponse.setEndereco(enderecoResponse);

        return cursoResponse;
    }


    public void removerCurso(Long id)
    {
        if (!this.repository.existsById(id)) {
            throw new EntityNotFoundException("Curso não encontrado com id: " + id);
        }
        this.repository.deleteById(id);
    }
}
