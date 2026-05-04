package school.sptech.cursos.DTO.Turma;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import school.sptech.cursos.DTO.EnderecoCurso.EnderecoCursoRequest;

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
    @Min(value = 20,message = "A quantidade minima de vagas é 20")
    @Max(value = 20,message = "A quantidade maxima de vagas é 20")
    private Integer qtdVagas;
    @NotNull
    private Boolean cursoIniciado;
    @Valid
    private EnderecoCursoRequest endereco;

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

    public Boolean getCursoIniciado() {
        return cursoIniciado;
    }

    public void setCursoIniciado(Boolean cursoIniciado) {
        this.cursoIniciado = cursoIniciado;
    }

    public EnderecoCursoRequest getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCursoRequest endereco) {
        this.endereco = endereco;
    }
}
