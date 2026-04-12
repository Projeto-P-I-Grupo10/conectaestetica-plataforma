package school.sptech.cursos.DTO.Curso;

import school.sptech.cursos.DTO.Area.AreaCursoResponse;
import school.sptech.cursos.DTO.EnderecoCurso.EnderecoCursoResponse;
import school.sptech.cursos.model.Curso;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CursoResponse {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataEncerramento;
    private String professor;
    private BigDecimal preco;
    private Integer qtdVagas;
    private Boolean cursoIniciado;
    private AreaCursoResponse area;
    private EnderecoCursoResponse endereco;

    public CursoResponse(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.descricao = curso.getDescricao();
        this.endereco = new EnderecoCursoResponse(curso.getEndereco());
        this.area = new AreaCursoResponse(curso.getArea());
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
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

    public AreaCursoResponse getArea() {
        return area;
    }

    public void setArea(AreaCursoResponse area) {
        this.area = area;
    }

    public EnderecoCursoResponse getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCursoResponse endereco) {
        this.endereco = endereco;
    }
}
