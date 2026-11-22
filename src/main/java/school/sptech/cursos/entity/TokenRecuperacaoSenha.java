package school.sptech.cursos.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

/**
 * Token de uso único para redefinição de senha.
 * Guardamos apenas o HASH (SHA-256) do token: se o banco vazar, os tokens não servem pra nada.
 */
@Entity
@Table(name = "token_recuperacao_senha")
public class TokenRecuperacaoSenha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String tokenHash;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    @OnDelete(action = OnDeleteAction.CASCADE) // apagar usuário apaga seus tokens
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime expiraEm;

    @Column(nullable = false)
    private boolean usado = false;

    public TokenRecuperacaoSenha() {
    }

    public TokenRecuperacaoSenha(String tokenHash, Usuario usuario, LocalDateTime expiraEm) {
        this.tokenHash = tokenHash;
        this.usuario = usuario;
        this.expiraEm = expiraEm;
    }

    public boolean estaValido() {
        return !usado && expiraEm.isAfter(LocalDateTime.now());
    }

    public Long getId() { return id; }
    public String getTokenHash() { return tokenHash; }
    public Usuario getUsuario() { return usuario; }
    public LocalDateTime getExpiraEm() { return expiraEm; }
    public boolean isUsado() { return usado; }
    public void setUsado(boolean usado) { this.usado = usado; }
}
