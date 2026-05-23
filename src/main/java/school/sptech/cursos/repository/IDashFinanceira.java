package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.cursos.model.Pagamento;
import school.sptech.cursos.projection.dashboard.DashFinanceiraProjection;

import java.util.List;

public interface IDashFinanceira extends JpaRepository<Pagamento, Long> {
    @Query(value = """
        SELECT
           t.id as idTurma,
           t.nome as nomeTurma,
           COUNT(p.id) AS totalVendas,
           SUM(p.valor) AS faturamentoTotal
           FROM pagamento p
           JOIN turma t
           ON p.id_turma = t.id
           WHERE p.status = 'approved'
           GROUP BY idTurma, nome
           ORDER BY totalVendas DESC
           LIMIT 5;
    """, nativeQuery = true)
    List<DashFinanceiraProjection> top5MaisVendidos();
}
