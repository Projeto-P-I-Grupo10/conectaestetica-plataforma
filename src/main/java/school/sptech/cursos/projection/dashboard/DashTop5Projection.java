package school.sptech.cursos.projection.dashboard;

import java.math.BigDecimal;

public interface DashTop5Projection {
        Long getIdTurma();
        String getNomeCurso();
        Integer getTotalVendas();
        BigDecimal getFaturamentoTotal();
}
