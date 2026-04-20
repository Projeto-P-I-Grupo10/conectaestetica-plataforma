package school.sptech.cursos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(length = 1000)
    private String descricao;

    private LocalDateTime dataInicio;

    private LocalDateTime dataEncerramento;

    private BigDecimal preco;

    private Integer qtdVagas;

    private Boolean cursoIniciado;

    private String imagem;

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private AreaCurso area;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private EnderecoCurso endereco;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;

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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public AreaCurso getArea() {
        return area;
    }

    public void setArea(AreaCurso area) {
        this.area = area;
    }

    public EnderecoCurso getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCurso endereco) {
        this.endereco = endereco;
    }
}
