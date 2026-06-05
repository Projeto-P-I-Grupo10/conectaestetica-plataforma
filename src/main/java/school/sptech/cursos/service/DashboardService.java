package school.sptech.cursos.service;

import org.springframework.stereotype.Service;
import school.sptech.cursos.dto.dashboard.DashFaturamentoTotalResponse;
import school.sptech.cursos.dto.dashboard.DashLucroSemanaResponse;
import school.sptech.cursos.dto.dashboard.DashTicketMedioResponse;
import school.sptech.cursos.projection.dashboard.DashQtdComprasNumDeterminadoIntervaloProjection;
import school.sptech.cursos.projection.dashboard.DashTop5Projection;
import school.sptech.cursos.projection.dashboard.DashTotalFaturamentoNaSemanaProjection;
import school.sptech.cursos.repository.IDashFinanceira;

import java.math.BigDecimal;
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

    public DashLucroSemanaResponse totalFaturamentoNaSemana() {
        List<DashTotalFaturamentoNaSemanaProjection> semana = dashFinanceira.qtdDeFaturamentoNaSemana();

        BigDecimal lucroSemanaPassada = BigDecimal.ZERO;
        BigDecimal lucroSemanaAtual = BigDecimal.ZERO;

        for(int i = 0; i < semana.size(); i++){
            if(semana.get(i).getPeriodo().equals("SemanaPassada")){
                lucroSemanaPassada = lucroSemanaPassada.add(semana.get(i).getFaturamentoTotal()
                        .multiply(BigDecimal.valueOf(semana.get(i).getPorcentagemLucro()))
                        .divide(BigDecimal.valueOf(100)));
            }else{
                lucroSemanaAtual = lucroSemanaAtual.add(semana.get(i).getFaturamentoTotal()
                        .multiply(BigDecimal.valueOf(semana.get(i).getPorcentagemLucro()))
                        .divide(BigDecimal.valueOf(100)));
            }
        }
        return new DashLucroSemanaResponse(lucroSemanaPassada, lucroSemanaAtual);
    }

    public DashFaturamentoTotalResponse faturamentoTotal() {
        return new DashFaturamentoTotalResponse(dashFinanceira.faturamentoTotal());
    }

    public DashTicketMedioResponse ticketMedio(){
        return new DashTicketMedioResponse(dashFinanceira.ticketMedio());
    }
}
