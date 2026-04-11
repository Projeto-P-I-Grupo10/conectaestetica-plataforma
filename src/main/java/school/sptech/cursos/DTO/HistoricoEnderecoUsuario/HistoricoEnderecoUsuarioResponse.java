package school.sptech.cursos.DTO.HistoricoEnderecoUsuario;

import java.time.LocalDateTime;

public class HistoricoEnderecoUsuarioResponse {

    private Long id;
    private String cep;
    private String numero;
    private String uf;
    private String rua;
    private String cidade;
    private String complemento;
    private Long usuarioId;
    private LocalDateTime dataPesquisa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
