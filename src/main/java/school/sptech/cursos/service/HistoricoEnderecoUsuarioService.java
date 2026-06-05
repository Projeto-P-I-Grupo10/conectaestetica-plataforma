package school.sptech.cursos.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.cursos.dto.historicoEnderecoUsuario.HistoricoEnderecoUsuarioCreateRequest;
import school.sptech.cursos.dto.historicoEnderecoUsuario.HistoricoEnderecoUsuarioResponse;
import school.sptech.cursos.entity.HistoricoEnderecoUsuario;
import school.sptech.cursos.entity.Usuario;
import school.sptech.cursos.repository.IHistoricoEnderecoUsuarioRepository;
import school.sptech.cursos.repository.IUsuarioRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricoEnderecoUsuarioService {

    private final IHistoricoEnderecoUsuarioRepository historicoRepository;
    private final IUsuarioRepository usuarioRepository;

    public HistoricoEnderecoUsuarioService(IHistoricoEnderecoUsuarioRepository historicoRepository, IUsuarioRepository usuarioRepository) {
        this.historicoRepository = historicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public HistoricoEnderecoUsuarioResponse salvar(HistoricoEnderecoUsuarioCreateRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        List<HistoricoEnderecoUsuario> enderecos = historicoRepository.findByUsuarioId(usuario.getId());

        for (HistoricoEnderecoUsuario e : enderecos) {
            if (Boolean.TRUE.equals(e.getEnderecoAtual())) {
                e.setEnderecoAtual(false);
                historicoRepository.save(e);
            }
        }

        HistoricoEnderecoUsuario historico = new HistoricoEnderecoUsuario();
        historico.setCep(request.getCep());
        historico.setNumero(request.getNumero());
        historico.setUf(request.getUf());
        historico.setRua(request.getRua());
        historico.setCidade(request.getCidade());
        historico.setComplemento(request.getComplemento());
        historico.setUsuario(usuario);

        historico.setEnderecoAtual(true);
        historicoRepository.save(historico);

        return new HistoricoEnderecoUsuarioResponse(historico);
    }

    public List<HistoricoEnderecoUsuarioResponse> listarPorUsuario(Long usuarioId) {
        List<HistoricoEnderecoUsuario> enderecos = historicoRepository.findByUsuarioId(usuarioId);
        List<HistoricoEnderecoUsuarioResponse> resposta = new ArrayList<>();

        for (HistoricoEnderecoUsuario enderecoAtual : enderecos) {
            resposta.add(new HistoricoEnderecoUsuarioResponse(enderecoAtual));
        }

        return resposta;
    }

    public HistoricoEnderecoUsuarioResponse buscarPorId(Long id) {
        HistoricoEnderecoUsuario historico = historicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Histórico não encontrado"));
        return new HistoricoEnderecoUsuarioResponse(historico);
    }

    public HistoricoEnderecoUsuarioResponse atualizar(Long id, HistoricoEnderecoUsuarioCreateRequest request) {
        HistoricoEnderecoUsuario historico = historicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Histórico não encontrado"));

        historico.setCep(request.getCep());
        historico.setNumero(request.getNumero());
        historico.setUf(request.getUf());
        historico.setRua(request.getRua());
        historico.setCidade(request.getCidade());
        historico.setComplemento(request.getComplemento());

        historicoRepository.save(historico);

        return new HistoricoEnderecoUsuarioResponse(historico);
    }

    public void deletar(Long id) {
        historicoRepository.deleteById(id);
    }

}
