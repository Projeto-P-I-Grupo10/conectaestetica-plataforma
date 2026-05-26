package school.sptech.cursos.service;

import org.springframework.stereotype.Service;
import school.sptech.cursos.projection.dashboard.DashQtdComprasNumDeterminadoIntervaloProjection;
import school.sptech.cursos.projection.dashboard.DashTop5Projection;
import school.sptech.cursos.repository.IDashFinanceira;

import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardService {

    private final IDashFinanceira dashFinanceira;

    public DashboardService(IDashFinanceira dashFinanceira) {
        this.dashFinanceira = dashFinanceira;
    }

    public List<DashTop5Projection> top5() {
        return dashFinanceira.top5MaisVendidos();
    }

    public List<DashQtdComprasNumDeterminadoIntervaloProjection> qtdVendasNumDeterminadoIntervalo(LocalDate dataInicio, LocalDate dataFim) {
        return dashFinanceira.qtdCursoVendidosNumIntervaloDeTempo(dataInicio, dataFim);
    }
}
