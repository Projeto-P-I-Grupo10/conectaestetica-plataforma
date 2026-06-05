package school.sptech.cursos.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDateTime dataInicio;

    private LocalDateTime dataEncerramento;

    private BigDecimal preco;

    private Integer qtdVagas;

    private Double porcentagemLucro;

    private Boolean cursoAtivo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private EnderecoCurso endereco;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    private Curso curso;

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

    public Double getPorcentagemLucro() { return porcentagemLucro; }

    public void setPorcentagemLucro(Double porcentagemLucro) {
        this.porcentagemLucro = porcentagemLucro;
    }

    public Boolean getCursoAtivo() {
        return cursoAtivo;
    }

    public void setCursoAtivo(Boolean cursoAtivo) {
        this.cursoAtivo = cursoAtivo;
    }

    public EnderecoCurso getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCurso endereco) {
        this.endereco = endereco;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
