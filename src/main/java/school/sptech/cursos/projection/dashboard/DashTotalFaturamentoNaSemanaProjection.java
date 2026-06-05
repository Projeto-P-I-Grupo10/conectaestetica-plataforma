package school.sptech.cursos.projection.dashboard;

import java.math.BigDecimal;

public interface DashTotalFaturamentoNaSemanaProjection {
    String getPeriodo();
    BigDecimal getFaturamentoTotal();
    Double getPorcentagemLucro();
}
