package school.sptech.cursos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cursos.projection.dashboard.DashQtdComprasNumDeterminadoIntervaloProjection;
import school.sptech.cursos.projection.dashboard.DashTop5Projection;
import school.sptech.cursos.projection.dashboard.DashTotalFaturamentoNaSemana;
import school.sptech.cursos.service.DashboardService;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dash")
public class DashboardController {

    private final DashboardService dash;

    public DashboardController(DashboardService dash) {
        this.dash = dash;
    }

    @GetMapping("/top5")
    public ResponseEntity<List<DashTop5Projection>> top5(){
        return ResponseEntity.status(200).body(dash.top5());
    }

    @GetMapping("/qtdVendasNumDeterminadoIntervalo/{dataInicio}/{dataFim}")
    public ResponseEntity<List<DashQtdComprasNumDeterminadoIntervaloProjection>> qtdVendasNumDeterminadoIntervalo(
            @PathVariable LocalDate dataInicio, @PathVariable LocalDate dataFim
            ){
        return ResponseEntity.status(200).body(dash.qtdVendasNumDeterminadoIntervalo(dataInicio, dataFim));
    }

    @GetMapping("/totalFaturamentoSemena")
    public ResponseEntity<List<DashTotalFaturamentoNaSemana>> totalFaturamentoSemena(){
        return ResponseEntity.status(200).body(dash.totalFaturamentoNaSemana());
    }

}
