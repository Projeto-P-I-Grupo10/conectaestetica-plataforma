package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cursos.model.Pagamento;

public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {
}
