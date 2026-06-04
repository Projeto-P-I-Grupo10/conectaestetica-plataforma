package school.sptech.cursos.dto.dashboard;

import java.math.BigDecimal;

public class DashFaturamentoTotalResponse {
    BigDecimal faturamentoTotal;

    public DashFaturamentoTotalResponse(BigDecimal faturamentoTotal) {
        this.faturamentoTotal = faturamentoTotal;
    }

    public BigDecimal getFaturamentoTotal() {
        return faturamentoTotal;
    }

    public void setFaturamentoTotal(BigDecimal faturamentoTotal) {
        this.faturamentoTotal = faturamentoTotal;
    }
}
