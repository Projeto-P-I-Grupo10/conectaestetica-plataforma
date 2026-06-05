package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.cursos.entity.Curso;

@Repository
public interface ICursoRepository extends JpaRepository<Curso,Long> {
}
