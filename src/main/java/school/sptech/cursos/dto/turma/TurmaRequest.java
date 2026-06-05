package school.sptech.cursos.dto.turma;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TurmaRequest {

    @NotBlank(message = "Nome da turma é obrigatório")
    private String nome;

    @FutureOrPresent(message = "Data de início deve ser no presente ou futuro")
    private LocalDateTime dataInicio;

    @NotNull(message = "Data de encerramento é obrigatória")
    @Future(message = "Data de encerramento deve ser no futuro")
    private LocalDateTime dataEncerramento;

    @Positive(message = "O preco deve ser maior que zero")
    private BigDecimal preco;

    @Positive(message = "quantidade de vagas deve ser maior zero")
    @Min(value = 20, message = "A quantidade minima de vagas é 20")
    @Max(value = 20, message = "A quantidade maxima de vagas é 20")
    private Integer qtdVagas;

    @Positive(message = "A porcentagem de lucro não pode ser negativa")
    @NotNull
    private Double porcentagemLucro;

    @NotNull
    private Boolean cursoAtivo;

    @NotNull
    private Long enderecoId;

    @NotNull
    private Long cursoId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQtdVagas() {
        return qtdVagas;
    }

    public void setQtdVagas(Integer qtdVagas) {
        this.qtdVagas = qtdVagas;
    }

    public Double getPorcentagemLucro() { return porcentagemLucro; }

    public void setPorcentagemLucro(Double porcentagemLucro) { this.porcentagemLucro = porcentagemLucro; }

    public Boolean getCursoAtivo() {
        return cursoAtivo;
    }

    public void setCursoAtivo(Boolean cursoAtivo) {
        this.cursoAtivo = cursoAtivo;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}
