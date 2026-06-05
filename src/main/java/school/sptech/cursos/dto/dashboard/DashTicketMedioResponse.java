package school.sptech.cursos.dto.dashboard;

import java.math.BigDecimal;

public class DashTicketMedioResponse {
    BigDecimal ticketMedio;

    public DashTicketMedioResponse(BigDecimal ticketMedio) {
        this.ticketMedio = ticketMedio;
    }

    public BigDecimal getTicketMedio() {
        return ticketMedio;
    }

    public void setTicketMedio(BigDecimal ticketMedio) {
        this.ticketMedio = ticketMedio;
    }
}
