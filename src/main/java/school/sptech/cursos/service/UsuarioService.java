package school.sptech.cursos.service;

import org.springframework.stereotype.Service;
import school.sptech.cursos.DTO.UsuarioDTO;
import school.sptech.cursos.model.Usuario;
import school.sptech.cursos.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario salvar(UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        if (repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        if (repository.existsByTelefone(dto.getTelefone())) {
            throw new RuntimeException("Telefone já cadastrado");
        }
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTelefone(dto.getTelefone());
        usuario.setTipo_usuario(dto.getTipo_usuario());

        return repository.save(usuario);
    }

    public Usuario buscarId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario buscarPorEmailESenha(String email, String senha) {
        return repository.findByEmailAndSenha(email, senha)
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));
    }

    public Usuario atualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = buscarId(id);


        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTelefone(dto.getTelefone());
        usuario.setTipo_usuario(dto.getTipo_usuario());

        return repository.save(usuario);
    }

    public void deletarPorID(Long id) {
        repository.deleteById(id);
    }
}
