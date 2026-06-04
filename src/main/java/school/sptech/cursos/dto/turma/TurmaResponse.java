package school.sptech.cursos.dto.turma;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import school.sptech.cursos.dto.curso.CursoResponse;
import school.sptech.cursos.dto.enderecoCurso.EnderecoCursoResponse;
import school.sptech.cursos.entity.Turma;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({
        "id",
        "nome",
        "descricao",
        "preco",
        "cursoIniciado",
        "dataInicio",
        "dataEncerramento",
        "qtdVagas",
        "area",
        "professor",
        "endereco"
})
public class TurmaResponse {

    private Long id;
    private String nome;
    private LocalDateTime dataInicio;
    private LocalDateTime dataEncerramento;
    private BigDecimal preco;
    private Integer qtdVagas;
    private Double porcentagemLucro;
    private Boolean cursoIniciado;
    private EnderecoCursoResponse endereco;
    private CursoResponse curso;

    public TurmaResponse(Turma turma) {
        this.id = turma.getId();
        this.nome = turma.getNome();
        this.dataInicio = turma.getDataInicio();
        this.dataEncerramento = turma.getDataEncerramento();
        this.preco = turma.getPreco();
        this.qtdVagas = turma.getQtdVagas();
        this.porcentagemLucro = turma.getPorcentagemLucro();
        this.cursoIniciado = turma.getCursoAtivo();
        this.endereco = new EnderecoCursoResponse(turma.getEndereco());
        this.curso = new CursoResponse(turma.getCurso());
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

    public Double getPorcentagemLucro() { return porcentagemLucro; }

    public void setCursoIniciado(Boolean cursoIniciado) {
        this.cursoIniciado = cursoIniciado;
    }

    public EnderecoCursoResponse getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCursoResponse endereco) {
        this.endereco = endereco;
    }
}
