package project.bookstore.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import project.bookstore.dto.BookDto;

import java.io.IOException;
import java.util.List;

public interface BookService {

    List<BookDto> getBooks(String name, String searchBy, String terms) throws IOException;

    BookDto getBookById(String id) throws JsonProcessingException;
}
