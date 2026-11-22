
package school.sptech.cursos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import school.sptech.cursos.entity.TokenRecuperacaoSenha;
import school.sptech.cursos.repository.ITokenRecuperacaoSenhaRepository;
import school.sptech.cursos.repository.IUsuarioRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HexFormat;

@Service
public class RecuperacaoSenhaService {

    private static final SecureRandom RANDOM = new SecureRandom();

    private final IUsuarioRepository usuarioRepository;
    private final ITokenRecuperacaoSenhaRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    @Value("${app.recuperacao-senha.expiracao-minutos}")
    private long expiracaoMinutos;

    public RecuperacaoSenhaService(IUsuarioRepository usuarioRepository,
                                   ITokenRecuperacaoSenhaRepository tokenRepository,
                                   PasswordEncoder passwordEncoder,
                                   EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    /**
     * Gera o token e dispara o e-mail. Se o e-mail não existir, não faz nada
     * (o controller responde sempre igual, para não revelar quais e-mails estão cadastrados).
     */
    @Transactional
    public void solicitarRecuperacao(String email) {
        usuarioRepository.findByEmail(email).ifPresent(usuario -> {
            // invalida pedidos anteriores: só o link mais recente vale
            tokenRepository.deleteByUsuarioId(usuario.getId());

            String token = gerarToken();
            tokenRepository.save(new TokenRecuperacaoSenha(
                    hash(token),
                    usuario,
                    LocalDateTime.now().plusMinutes(expiracaoMinutos)
            ));

            String link = frontendUrl + "/redefinir-senha?token=" + token;
            emailService.enviarRecuperacaoSenha(usuario.getEmail(), usuario.getNome(), link, expiracaoMinutos);
        });
    }

    @Transactional
    public void redefinirSenha(String token, String novaSenha) {
        TokenRecuperacaoSenha registro = tokenRepository.findByTokenHash(hash(token))
                .filter(TokenRecuperacaoSenha::estaValido)
                .orElseThrow(() -> new IllegalArgumentException("Link inválido ou expirado"));

        var usuario = registro.getUsuario();
        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);

        // uso único: apaga este e qualquer outro token pendente do usuário
        tokenRepository.deleteByUsuarioId(usuario.getId());

        // Envia o aviso somente depois da confirmação da transação
        String email = usuario.getEmail();
        String nome = usuario.getNome();

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                emailService.enviarSenhaAlterada(email, nome);
            }
        });
    }

    private String gerarToken() {
        byte[] bytes = new byte[32]; // 256 bits de entropia
        RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private String hash(String token) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(sha.digest(token.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 indisponível", e);
        }
    }
}
