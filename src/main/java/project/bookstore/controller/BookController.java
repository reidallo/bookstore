package project.bookstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.bookstore.dto.BookDto;
import project.bookstore.service.BookService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/books")
    public ResponseEntity<List<BookDto>> getBooks(
            @RequestParam String name,
            @RequestParam String searchBy,
            @RequestParam String terms)throws IOException {
        return ResponseEntity.ok(bookService.getBooks(name, searchBy, terms));
    }

    @GetMapping(value = "/book/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable String id) throws JsonProcessingException {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
}