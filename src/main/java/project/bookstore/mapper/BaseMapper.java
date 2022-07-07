package project.bookstore.mapper;

public interface BaseMapper<E, D> {
    E toEntity(D p);
    D toDto(E e);
}
