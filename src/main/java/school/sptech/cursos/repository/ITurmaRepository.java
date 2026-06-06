package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.cursos.entity.Turma;
import school.sptech.cursos.projection.TurmaDetalhesProjection;

import java.util.List;
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
            c.id                AS cursoId,
            c.imagem            AS cursoImagem,
            p.nome              AS professorNome,
            p.foto              AS professorFoto,
            p.descricao         AS professorDescricao,
            p.redesocial        AS professorRedesocial,
            ac.nome             AS areaNome,
            e.rua               AS enderecoRua,
            e.numero            AS enderecoNumero,
            e.cidade            AS enderecoCidade
        FROM turma t
        JOIN curso c
            ON c.id = t.curso_id
        JOIN professor p
            ON p.id = c.professor_id
        JOIN area_curso ac
            ON ac.id = c.area_id
        LEFT JOIN endereco_curso e
            ON e.id = t.endereco_id
    """, nativeQuery = true)
    Optional<TurmaDetalhesProjection> buscarDetalhesCurso(@Param("id") Long id);

    @Query(value = """
        SELECT
            t.nome              AS turmaNome,
            t.curso_ativo       AS turmaCursoAtivo,
            t.data_encerramento AS turmaDataEncerramento,
            t.data_inicio       AS turmaDataInicio,
            t.qtd_vagas         AS turmaQtdVagas,
            t.preco             AS turmaPreco,

            c.nome              AS cursoNome,
            c.descricao         AS cursoDescricao,
            c.id                AS cursoId,
            c.imagem            AS cursoImagem,

            p.nome              AS professorNome,
            p.foto              AS professorFoto,
            p.descricao         AS professorDescricao,
            p.redesocial        AS professorRedesocial,

            ac.nome             AS areaNome,
            
            e.rua               AS enderecoRua,
            e.numero            AS enderecoNumero,
            e.cidade            AS enderecoCidade
                        
        FROM turma t
        JOIN curso c 
            ON c.id = t.curso_id
        JOIN professor p 
            ON p.id = c.professor_id
        JOIN area_curso ac 
            ON ac.id = c.area_id
        LEFT JOIN endereco_curso e
            ON e.id = t.endereco_id
    """, nativeQuery = true)
    List<TurmaDetalhesProjection> buscarTodosDetalhes();

    @Query(value = """
            SELECT
             t.nome              AS turmaNome,
             t.curso_ativo       AS turmaCursoAtivo,
             t.data_encerramento AS turmaDataEncerramento,
             t.data_inicio       AS turmaDataInicio,
             t.qtd_vagas         AS turmaQtdVagas,
             t.preco             AS turmaPreco,
            
             c.nome              AS cursoNome,
             c.descricao         AS cursoDescricao,
             c.id                AS cursoId,
             c.imagem            AS cursoImagem,
            
             p.nome              AS professorNome,
             p.foto              AS professorFoto,
             p.descricao         AS professorDescricao,
             p.redesocial        AS professorRedesocial,
            
             ac.nome             AS areaNome,
            
             e.rua               AS enderecoRua,
             e.numero            AS enderecoNumero,
             e.cidade            AS enderecoCidade
            
             FROM turma t
             JOIN curso c
             ON c.id = t.curso_id
             JOIN professor p
             ON p.id = c.professor_id
             JOIN area_curso ac
             ON ac.id = c.area_id
             LEFT JOIN endereco_curso e
             ON e.id = t.endereco_id
             ORDER BY turmaDataInicio DESC;
    """, nativeQuery = true)
    List<TurmaDetalhesProjection> buscarTodosDetalhesRecentes();

    @Query(value = """
            SELECT
             t.nome              AS turmaNome,
             t.curso_ativo       AS turmaCursoAtivo,
             t.data_encerramento AS turmaDataEncerramento,
             t.data_inicio       AS turmaDataInicio,
             t.qtd_vagas         AS turmaQtdVagas,
             t.preco             AS turmaPreco,
            
             c.nome              AS cursoNome,
             c.descricao         AS cursoDescricao,
             c.id                AS cursoId,
             c.imagem            AS cursoImagem,
            
             p.nome              AS professorNome,
             p.foto              AS professorFoto,
             p.descricao         AS professorDescricao,
             p.redesocial        AS professorRedesocial,
            
             ac.nome             AS areaNome,
            
             e.rua               AS enderecoRua,
             e.numero            AS enderecoNumero,
             e.cidade            AS enderecoCidade
            
             FROM turma t
             JOIN curso c
             ON c.id = t.curso_id
             JOIN professor p
             ON p.id = c.professor_id
             JOIN area_curso ac
             ON ac.id = c.area_id
             LEFT JOIN endereco_curso e
             ON e.id = t.endereco_id
             ORDER BY turmaPreco;
    """, nativeQuery = true)
    List<TurmaDetalhesProjection> buscarTodosDetalhesPorPreco();

    @Query(value = """
            SELECT
              t.nome              AS turmaNome,
              t.curso_ativo       AS turmaCursoAtivo,
              t.data_encerramento AS turmaDataEncerramento,
              t.data_inicio       AS turmaDataInicio,
              t.qtd_vagas         AS turmaQtdVagas,
              t.preco             AS turmaPreco,
            
              c.nome              AS cursoNome,
              c.descricao         AS cursoDescricao,
              c.id                AS cursoId,
              c.imagem            AS cursoImagem,
            
              p.nome              AS professorNome,
              p.foto              AS professorFoto,
              p.descricao         AS professorDescricao,
              p.redesocial        AS professorRedesocial,
            
              ac.nome             AS areaNome,
            
              e.rua               AS enderecoRua,
              e.numero            AS enderecoNumero,
              e.cidade            AS enderecoCidade,
            
              ava.avaliacao		 AS avaliacaoCurso
            
              FROM turma t
              JOIN curso c
              ON c.id = t.curso_id
              JOIN professor p
              ON p.id = c.professor_id
              JOIN area_curso ac
              ON ac.id = c.area_id
              LEFT JOIN endereco_curso e
              ON e.id = t.endereco_id
              JOIN avaliacao_curso ava
              ON ava.curso_id = c.id
              ORDER BY avaliacaoCurso DESC;
    """, nativeQuery = true)
    List<TurmaDetalhesProjection> buscarTodosDetalhesPorAvalicao();

    @Query(value = """
        SELECT
            t.nome              AS turmaNome,
            t.curso_ativo       AS turmaCursoAtivo,
            t.data_encerramento AS turmaDataEncerramento,
            t.data_inicio       AS turmaDataInicio,
            t.qtd_vagas         AS turmaQtdVagas,
            t.preco             AS turmaPreco,

            c.nome              AS cursoNome,
            c.descricao         AS cursoDescricao,
            c.id                AS cursoId,
            c.imagem            AS cursoImagem,

            p.nome              AS professorNome,
            p.foto              AS professorFoto,
            p.descricao         AS professorDescricao,
            p.redesocial        AS professorRedesocial,

            ac.nome             AS areaNome,
            
            e.rua               AS enderecoRua,
            e.numero            AS enderecoNumero,
            e.cidade            AS enderecoCidade
                        
        FROM turma t
        JOIN curso c
        ON c.id = t.curso_id
        JOIN professor p
        ON p.id = c.professor_id
        JOIN area_curso ac
        ON ac.id = c.area_id
        LEFT JOIN endereco_curso e
        ON e.id = t.endereco_id
        JOIN avaliacao_curso ava
        ON ava.curso_id = c.id
        WHERE ac.nome = :area
        ORDER BY turmaDataInicio DESC;
    """, nativeQuery = true)
    List<TurmaDetalhesProjection> buscarTodosDetalhesPorArea(@Param("area") String area);


}
