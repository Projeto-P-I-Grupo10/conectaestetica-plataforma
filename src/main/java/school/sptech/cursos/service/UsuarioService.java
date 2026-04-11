package school.sptech.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.cursos.Config.GerenciadorTokenJwt;
import school.sptech.cursos.DTO.Usuario.UsuarioRequest;
import school.sptech.cursos.DTO.Usuario.UsuarioResponse;
import school.sptech.cursos.DTO.Usuario.UsuarioToken;
import school.sptech.cursos.model.Usuario;
import school.sptech.cursos.repository.IUsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final IUsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

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
        String senhaCriptografada = passwordEncoder.encode(request.getSenha());
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(senhaCriptografada);
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

    public UsuarioToken autenticar(String email, String senha) {
        var credentials = new UsernamePasswordAuthenticationToken(email, senha);
        Authentication authentication = authenticationManager.authenticate(credentials);

        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email não cadastrado"));

        String token = gerenciadorTokenJwt.generateToken(authentication);

        UsuarioToken response = new UsuarioToken();
        response.setId(usuario.getId());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setTelefone(usuario.getTelefone());
        response.setTipoUsuario(usuario.getTipoUsuario());
        response.setToken(token);

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
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
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
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        repository.deleteById(id);
    }
}
