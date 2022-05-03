package project.bookstore.mapper;

public interface BaseMapper<K, P> {
    K toEntity(P p);
    P toDto(K k);
}
