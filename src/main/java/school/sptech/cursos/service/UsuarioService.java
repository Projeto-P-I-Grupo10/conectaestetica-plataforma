package school.sptech.cursos.service;

import org.springframework.stereotype.Service;
import school.sptech.cursos.DTO.Usuario.UsuarioRequest;
import school.sptech.cursos.DTO.Usuario.UsuarioResponse;
import school.sptech.cursos.model.Usuario;
import school.sptech.cursos.repository.IUsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final IUsuarioRepository repository;

    public UsuarioService(IUsuarioRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioResponse> listar() {
        List<Usuario> usuarios = repository.findAll();
        List<UsuarioResponse> resposta = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioResponse response = new UsuarioResponse();

            response.setId(usuario.getId());
            response.setNome(usuario.getNome());
            response.setEmail(usuario.getEmail());
            response.setTelefone(usuario.getTelefone());
            response.setTipoUsuario(usuario.getTipoUsuario());

            resposta.add(response);
        }

        return resposta;
    }

    public UsuarioResponse salvar(UsuarioRequest request) {
        Usuario usuario = new Usuario();

        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        if (repository.existsByTelefone(request.getTelefone())) {
            throw new RuntimeException("Telefone já cadastrado");
        }
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());
        usuario.setTelefone(request.getTelefone());
        usuario.setTipoUsuario(request.getTipoUsuario());
        usuario = repository.save(usuario);

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setTelefone(usuario.getTelefone());
        response.setTipoUsuario(usuario.getTipoUsuario());

        return response;
    }

    public UsuarioResponse buscarId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setTelefone(usuario.getTelefone());
        response.setTipoUsuario(usuario.getTipoUsuario());

        return response;

    }

    public UsuarioResponse buscarPorEmailESenha(String email, String senha) {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        if (!senha.equals(usuario.getSenha())) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setTelefone(usuario.getTelefone());
        response.setTipoUsuario(usuario.getTipoUsuario());

        return response;
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario inválido"));

        if (!usuario.getEmail().equals(request.getEmail()) &&
                repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (!usuario.getTelefone().equals(request.getTelefone()) &&
                repository.existsByTelefone(request.getTelefone())) {
            throw new RuntimeException("Telefone já cadastrado");
        }

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha()); // tem qu cripitografar -> passar pr ocaiobaaa ou henry
        usuario.setTelefone(request.getTelefone());
        usuario.setTipoUsuario(request.getTipoUsuario());

        usuario = repository.save(usuario);

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setTelefone(usuario.getTelefone());
        response.setTipoUsuario(usuario.getTipoUsuario());

        return response;
    }

    public void deletarPorID(Long id) {
        repository.deleteById(id);
    }
}
