package school.sptech.cursos.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.cursos.config.GerenciadorTokenJwt;
import school.sptech.cursos.dto.usuario.UsuarioRequest;
import school.sptech.cursos.dto.usuario.UsuarioResponse;
import school.sptech.cursos.entity.Usuario;
import school.sptech.cursos.repository.IUsuarioRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("UsuarioService")
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private IUsuarioRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UsuarioService service;

//    @Test
//    @DisplayName("Deve salvar usuário com sucesso")
//    void deveSalvarUsuario() {
//
//        // Arrange
//        UsuarioRequest request = new UsuarioRequest();
//        request.setNome("Kevin");
//        request.setEmail("kevin@email.com");
//        request.setTelefone("11999999999");
//        request.setSenha("123456");
//
//        Usuario usuarioSalvo = new Usuario();
//        usuarioSalvo.setId(1L);
//        usuarioSalvo.setNome("Kevin");
//        usuarioSalvo.setEmail("kevin@email.com");
//        usuarioSalvo.setTelefone("11999999999");
//
//        when(repository.existsByEmail(request.getEmail()))
//                .thenReturn(false);
//
//        when(repository.existsByTelefone(request.getTelefone()))
//                .thenReturn(false);
//
//        when(passwordEncoder.encode("123456"))
//                .thenReturn("senha-criptografada");
//
//        when(repository.save(any(Usuario.class)))
//                .thenReturn(usuarioSalvo);
//
//        // Act
//        UsuarioResponse response = service.salvar(request);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(1L, response.getId());
//        assertEquals("Kevin", response.getNome());
//
//        verify(repository).save(any(Usuario.class));
//    }
}