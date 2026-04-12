package school.sptech.cursos.service;

import org.springframework.stereotype.Service;
import school.sptech.cursos.DTO.HistoricoEnderecoUsuario.HistoricoEnderecoUsuarioCreateRequest;
import school.sptech.cursos.DTO.HistoricoEnderecoUsuario.HistoricoEnderecoUsuarioRequest;
import school.sptech.cursos.DTO.HistoricoEnderecoUsuario.HistoricoEnderecoUsuarioResponse;
import school.sptech.cursos.model.HistoricoEnderecoUsuario;
import school.sptech.cursos.model.Usuario;
import school.sptech.cursos.repository.IHistoricoEnderecoUsuarioRepository;
import school.sptech.cursos.repository.IUsuarioRepository;

import java.time.LocalDateTime;

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
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        HistoricoEnderecoUsuario historico = new HistoricoEnderecoUsuario();

        historico.setCep(request.getCep());
        historico.setNumero(request.getNumero());
        historico.setUf(request.getUf());
        historico.setRua(request.getRua());
        historico.setCidade(request.getCidade());
        historico.setComplemento(request.getComplemento());
        historico.setUsuario(usuario);
        historico.setDataPesquisa(LocalDateTime.now());
        this.historicoRepository.save(historico);

        HistoricoEnderecoUsuarioResponse response = new HistoricoEnderecoUsuarioResponse();
        response.setId(historico.getId());
        response.setCep(historico.getCep());
        response.setNumero(historico.getNumero());
        response.setUf(historico.getUf());
        response.setRua(historico.getRua());
        response.setCidade(historico.getCidade());
        response.setComplemento(historico.getComplemento());
        response.setUsuarioId(historico.getUsuario().getId());
        response.setDataPesquisa(historico.getDataPesquisa());

        return response;
    }
}
