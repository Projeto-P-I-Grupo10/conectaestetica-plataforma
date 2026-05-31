package school.sptech.cursos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(length = 1000)
    private String descricao;

    private String imagem;

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private AreaCurso area;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;

    private Integer porcentagem_lucro;

    @OneToMany(mappedBy = "curso")
    private List<AvaliacaoCurso> avaliacoes;

    public List<AvaliacaoCurso> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoCurso> avaliacoes) {
        this.avaliacoes = avaliacoes;
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public BigDecimal getPorcentagem_lucro() {return porcentagem_lucro;}

    public void setPorcentagem_lucro(BigDecimal porcentagem_lucro) {this.porcentagem_lucro = porcentagem_lucro;}
}
