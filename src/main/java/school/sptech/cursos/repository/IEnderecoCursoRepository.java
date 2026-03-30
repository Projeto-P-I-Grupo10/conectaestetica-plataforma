package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.cursos.model.EnderecoCurso;

@Repository
public interface IEnderecoCursoRepository extends JpaRepository<EnderecoCurso,Long> {
}