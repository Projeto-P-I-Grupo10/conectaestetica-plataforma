package school.sptech.cursos.projection.dashboard;

import java.math.BigDecimal;

public interface DashFinanceiraProjection {
        Long getIdTurma();
        String getNomeTurma();
        Integer getTotalVendas();
        BigDecimal getFaturamentoTotal();
}
