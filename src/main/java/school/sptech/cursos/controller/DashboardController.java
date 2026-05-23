package school.sptech.cursos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.cursos.projection.dashboard.DashFinanceiraProjection;
import school.sptech.cursos.service.DashboardService;

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
    public ResponseEntity<List<DashFinanceiraProjection>> top5(){
        return ResponseEntity.status(200).body(dash.top5());
    }
}
