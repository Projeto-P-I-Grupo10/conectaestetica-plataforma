package school.sptech.cursos.DTO.Curso;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import school.sptech.cursos.DTO.Area.AreaCursoRequest;
import school.sptech.cursos.DTO.EnderecoCurso.EnderecoCursoRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CursoRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Descricao é obrigatória")
    private String descricao;
    @NotNull(message = "Data de início é obrigatória")
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
    private String imagem;
    @Valid
    private AreaCursoRequest areaCurso;
    @Valid
    private EnderecoCursoRequest endereco;
    @NotNull
    private Long professorId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public AreaCursoRequest getAreaCurso() {
        return areaCurso;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setAreaCurso(AreaCursoRequest areaCurso) {
        this.areaCurso = areaCurso;
    }

    public EnderecoCursoRequest getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCursoRequest endereco) {
        this.endereco = endereco;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }
}
