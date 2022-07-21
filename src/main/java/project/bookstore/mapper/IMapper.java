package project.bookstore.mapper;

import java.util.List;

public interface IMapper<E, D> {

    E toEntity(D dto);
    D toDto(E entity);
    List<D> toDtoList(List<E> entityList);
}
