package school.sptech.cursos.service;

import org.springframework.stereotype.Service;
import school.sptech.cursos.projection.dashboard.DashFinanceiraProjection;
import school.sptech.cursos.repository.IDashFinanceira;

import java.util.List;

@Service
public class DashboardService {

    private final IDashFinanceira dashFinanceira;

    public DashboardService(IDashFinanceira dashFinanceira) {
        this.dashFinanceira = dashFinanceira;
    }

    public List<DashFinanceiraProjection> top5() {
        return dashFinanceira.top5MaisVendidos();
    }
}
