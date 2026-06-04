package school.sptech.cursos.projection.dashboard;

import java.math.BigDecimal;

public interface DashTotalFaturamentoNaSemana {
    String getPeriodo();
    BigDecimal getFaturamentoTotal();
    Double getPorcentagemLucro();
}
