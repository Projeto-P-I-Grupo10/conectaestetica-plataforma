package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.cursos.model.Turma;
import school.sptech.cursos.projection.TurmaDetalhesProjection;

import java.util.Optional;

public interface ITurmaRepository extends JpaRepository<Turma,Long> {
    @Query(value = """
        SELECT
            t.nome              AS turmaNome,
            t.curso_ativo    AS turmaCursoAtivo,
            t.data_encerramento AS turmaDataEncerramento,
            t.data_inicio       AS turmaDataInicio,
            t.qtd_vagas         AS turmaQtdVagas,
            t.preco             AS turmaPreco,
            c.nome              AS cursoNome,
            c.descricao         AS cursoDescricao,
            c.imagem            AS cursoImagem,
            p.nome              AS professorNome,
            p.foto              AS professorFoto,
            p.descricao         AS professorDescricao,
            p.redesocial        AS professorRedesocial,
            ac.nome             AS areaNome
        FROM turma t
        JOIN curso c 
            ON c.id = t.curso_id
        JOIN professor p 
            ON p.id = c.professor_id
        JOIN area_curso ac 
            ON ac.id = c.area_id
        WHERE t.id = :id
    """, nativeQuery = true)
    Optional<TurmaDetalhesProjection> buscarDetalhesCurso(@Param("id") Long id);

}
