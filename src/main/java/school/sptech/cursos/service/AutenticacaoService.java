package school.sptech.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.cursos.model.Usuario;
import school.sptech.cursos.repository.IUsuarioRepository;
import school.sptech.cursos.DTO.Usuario.UsuarioDetalhes;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {


    private final IUsuarioRepository usuarioRepository;

    public AutenticacaoService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(username);

        if (usuarioOpt.isEmpty()) {

            throw new UsernameNotFoundException(String.format("usuário: %s não encontrado", username));
        }

        return new UsuarioDetalhes(usuarioOpt.get());
    }
}