package project.bookstore.mapper.implementation;

import org.springframework.stereotype.Component;
import project.bookstore.dto.BookDto;
import project.bookstore.mapper.BookMapper;
import project.bookstore.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book toEntity(BookDto dto) {

        if (dto == null)
            return null;

        Book entity = new Book();
        if (dto.getId() != null)
            entity.setId(dto.getId());
        if (dto.getTitle() != null)
            entity.setTitle(dto.getTitle());
        if (dto.getDescription() != null)
            entity.setDescription(dto.getDescription());
        if (dto.getPrice() != null)
            entity.setPrice(dto.getPrice());
        if (dto.getAuthors() != null)
            entity.setAuthors(dto.getAuthors());
        if (dto.getCategories() != null)
            entity.setCategories(dto.getCategories());
        return entity;
    }

    @Override
    public BookDto toDto(Book entity) {

        if (entity == null)
            return null;

        BookDto dto = new BookDto();
        if (entity.getId() != null)
            dto.setId(entity.getId());
        if (entity.getTitle() != null)
            dto.setTitle(entity.getTitle());
        if (entity.getDescription() != null)
            dto.setDescription(entity.getDescription());
        if (entity.getPrice() != null)
            dto.setPrice(entity.getPrice());
        if (entity.getAuthors() != null)
            dto.setAuthors(entity.getAuthors());
        if (entity.getCategories() != null)
            dto.setCategories(entity.getCategories());
        return dto;

    }

    @Override
    public List<BookDto> toDtoList(List<Book> entityList) {

        if (entityList == null)
            return null;

        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
