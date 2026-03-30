package school.sptech.cursos.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.cursos.enums.UsuarioEnum;

public class UsuarioDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha precisa ter no mínimo 6 caracteres")
    private String senha;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotNull(message = "Tipo de usuário é obrigatório")
    private UsuarioEnum tipo_usuario;

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
        return tipo_usuario;
    }

    public void setTipo_usuario(UsuarioEnum tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    }
