package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.cursos.entity.HistoricoEnderecoUsuario;

import java.util.List;


@Repository
public interface IHistoricoEnderecoUsuarioRepository extends JpaRepository<HistoricoEnderecoUsuario,Long> {
    List<HistoricoEnderecoUsuario> findByUsuarioId(Long usuarioId);


}
