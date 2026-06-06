package school.sptech.cursos.dto.historicoEnderecoUsuario;

import school.sptech.cursos.entity.HistoricoEnderecoUsuario;

public class HistoricoEnderecoUsuarioResponse {

    private Long id;
    private String cep;
    private String numero;
    private String uf;
    private String rua;
    private String cidade;
    private String complemento;
    private Boolean enderecoAtual;

    public HistoricoEnderecoUsuarioResponse() {
    }

    public HistoricoEnderecoUsuarioResponse(HistoricoEnderecoUsuario historicoEnderecoUsuario) {
        this.id = historicoEnderecoUsuario.getId();
        this.cep = historicoEnderecoUsuario.getCep();
        this.numero = historicoEnderecoUsuario.getNumero();
        this.uf = historicoEnderecoUsuario.getUf();
        this.rua = historicoEnderecoUsuario.getRua();
        this.cidade = historicoEnderecoUsuario.getCidade();
        this.complemento = historicoEnderecoUsuario.getComplemento();
        this.enderecoAtual = historicoEnderecoUsuario.getEnderecoAtual();
    }

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

    public Boolean getEnderecoAtual() {
        return enderecoAtual;
    }

    public void setEnderecoAtual(Boolean enderecoAtual) {
        this.enderecoAtual = enderecoAtual;
    }
}
