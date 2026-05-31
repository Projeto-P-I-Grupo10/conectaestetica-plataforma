package school.sptech.cursos.model;

import jakarta.persistence.*;

@Entity
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "matricula_id", referencedColumnName = "id")
    private Matricula matricula;

    private String arquivo;
}
