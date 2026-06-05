package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.cursos.entity.AreaCurso;

@Repository
public interface IAreaCursoRepository extends JpaRepository<AreaCurso,Long> {
}
