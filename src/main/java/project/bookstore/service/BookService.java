package project.bookstore.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import project.bookstore.dto.BookDtoOut;

import java.io.IOException;
import java.util.List;

public interface BookService {

    List<BookDtoOut> getBooks(String name, String searchBy, String terms) throws IOException;

    BookDtoOut getBookById(String id) throws JsonProcessingException;
}
