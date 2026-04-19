package school.sptech.cursos.service;

import school.sptech.cursos.repository.IPagamentoRepository;

public class PagamentoService {

    private final IPagamentoRepository pagamentoRepository;

    public PagamentoService(IPagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }
}
