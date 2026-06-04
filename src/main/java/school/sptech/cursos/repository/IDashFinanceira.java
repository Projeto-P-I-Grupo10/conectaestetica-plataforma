package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.cursos.entity.Pagamento;
import school.sptech.cursos.projection.dashboard.DashQtdComprasNumDeterminadoIntervaloProjection;
import school.sptech.cursos.projection.dashboard.DashTop5Projection;
import school.sptech.cursos.projection.dashboard.DashTotalFaturamentoNaSemana;

import java.time.LocalDate;
import java.util.List;

public interface IDashFinanceira extends JpaRepository<Pagamento, Long> {
    @Query(value = """
        SELECT
              t.id as idTurma,
              c.nome as nomeCurso,
              COUNT(p.id) AS totalVendas,
              SUM(p.valor) AS faturamentoTotal
              FROM pagamento p
               JOIN turma t
                   ON p.id_turma = t.id
               JOIN curso c
                   ON t.curso_id = c.id
               WHERE p.status = 'approved'
               GROUP BY idTurma, nomeCurso
               ORDER BY totalVendas DESC
               LIMIT 5;
    """, nativeQuery = true)
    List<DashTop5Projection> top5MaisVendidos();

    @Query(value = """
        WITH RECURSIVE calendario AS (
                    SELECT CAST(:dataInicio AS DATE) AS data_gerada
                    UNION ALL
                    SELECT DATE_ADD(data_gerada, INTERVAL 1 DAY)
                    FROM calendario
                    WHERE data_gerada < CAST(:dataFim AS DATE)
                )
                SELECT
                    c.data_gerada AS dataCompra,
                    COUNT(p.id) AS qtdCompras
                FROM calendario c
                LEFT JOIN pagamento p
                    ON p.data_pagamento >= c.data_gerada
                    AND p.data_pagamento < DATE_ADD(c.data_gerada, INTERVAL 1 DAY)
                GROUP BY c.data_gerada;
    """, nativeQuery = true)
    List<DashQtdComprasNumDeterminadoIntervaloProjection> qtdCursoVendidosNumIntervaloDeTempo(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );
    @Query(value = """
            SELECT
                    'SemanaPassada' AS periodo,
                    SUM(valor) AS faturamentoTotal
                FROM pagamento
                WHERE status = 'approved'
                  AND YEARWEEK(data_pagamento, 1) = YEARWEEK(CURDATE(), 1) - 1
                UNION ALL
                SELECT
                    'SemanaAtual' AS periodo,
                    SUM(valor) AS faturamento_total
                FROM pagamento
                WHERE status = 'approved'
                  AND YEARWEEK(data_pagamento, 1) = YEARWEEK(CURDATE(), 1);
    """, nativeQuery = true)
    List<DashTotalFaturamentoNaSemana> qtdDeFaturamentoNaSemana();


}
