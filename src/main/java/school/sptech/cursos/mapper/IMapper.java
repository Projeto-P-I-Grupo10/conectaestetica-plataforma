package school.sptech.cursos.mapper;

public interface IMapper <E, Req, Res>{
    E toEntity(Req req);

    Res toResponse(E e);
}
