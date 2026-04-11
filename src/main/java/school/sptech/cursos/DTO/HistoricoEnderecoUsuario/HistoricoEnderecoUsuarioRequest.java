package school.sptech.cursos.DTO.HistoricoEnderecoUsuario;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class HistoricoEnderecoUsuarioRequest {

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato NNNNN-NNN")
    private String cep;
    @NotBlank(message = "Número é obrigatório")
    @Size(max = 10, message = "Número deve ter no máximo 10 caracteres")
    private String numero;
    @NotBlank(message = "UF é obrigatória")
    @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres")
    private String uf;
    @NotBlank(message = "Rua é obrigatória")
    @Size(max = 100, message = "Rua deve ter no máximo 100 caracteres")
    private String rua;
    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
    private String cidade;
    @Size(max = 100, message = "Complemento deve ter no máximo 100 caracteres")
    private String complemento;
    @NotNull(message = "UsuarioId é obrigatório")
    @Positive(message = "UsuarioId deve ser positivo")
    private Long usuarioId;
    private LocalDateTime dataPesquisa;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDateTime getDataPesquisa() {
        return dataPesquisa;
    }

    public void setDataPesquisa(LocalDateTime dataPesquisa) {
        this.dataPesquisa = dataPesquisa;
    }
}
