package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cursos.entity.Documento;

public interface IDocumentoRepository extends JpaRepository<Documento, Long> {
}
