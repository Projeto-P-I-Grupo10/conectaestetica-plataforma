package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cursos.model.Matricula;

public interface IMatriculaRepository extends JpaRepository<Matricula, Long> {
}
