package school.sptech.cursos.model;

import jakarta.persistence.*;
import school.sptech.cursos.enums.UsuarioEnum;

import java.util.ArrayList;
import java.util.List;

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
    @Column(unique = true)
    private String telefone;
    @Enumerated(EnumType.STRING)
    private UsuarioEnum tipoUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<HistoricoEnderecoUsuario> endereco = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, String telefone, UsuarioEnum tipoUsuario, List<HistoricoEnderecoUsuario> endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
        this.endereco = endereco;
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

    public UsuarioEnum getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(UsuarioEnum tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<HistoricoEnderecoUsuario> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<HistoricoEnderecoUsuario> endereco) {
        this.endereco = endereco;
    }
}
