package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cursos.model.HistoricoEnderecoUsuario;


public interface IHistoricoEnderecoUsuarioRepository extends JpaRepository<HistoricoEnderecoUsuario,Long> {
}
