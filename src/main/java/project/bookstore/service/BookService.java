package project.bookstore.service;

import project.bookstore.dto.BookDtoOut;

import java.io.IOException;
import java.util.List;

public interface BookService {

    List<BookDtoOut> getBookByTitle(String search, String terms) throws IOException;
}
