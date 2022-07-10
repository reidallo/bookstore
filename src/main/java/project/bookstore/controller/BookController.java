package project.bookstore.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.bookstore.dto.BookDtoOut;
import project.bookstore.service.BookService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/books")
    public ResponseEntity<List<BookDtoOut>> getBooks(
            @RequestParam String search,
            @RequestParam String terms) throws IOException {
        return ResponseEntity.ok(bookService.getBookByTitle(search, terms));
    }
}
