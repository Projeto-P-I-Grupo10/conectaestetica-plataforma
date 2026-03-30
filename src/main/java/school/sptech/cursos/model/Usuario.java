package school.sptech.cursos.model;

import jakarta.persistence.*;
import school.sptech.cursos.enums.UsuarioEnum;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private UsuarioEnum tipoUsuario;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, String telefone, UsuarioEnum tipo_usuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipoUsuario = tipo_usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public UsuarioEnum getTipo_usuario() {
        return tipoUsuario;
    }

    public void setTipo_usuario(UsuarioEnum tipo_usuario) {
        this.tipoUsuario = tipo_usuario;
    }
    }
