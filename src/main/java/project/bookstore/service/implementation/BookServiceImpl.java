package project.bookstore.service.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.bookstore.dto.AuthorDtoOut;
import project.bookstore.dto.BookDtoOut;
import project.bookstore.service.BookService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Value("${API_KEY}")
    private String API_KEY;

    @Override
    public List<BookDtoOut> getBookByTitle(String search, String terms) throws IOException {

        String url = "https://www.googleapis.com/books/v1/volumes?q=" + search + "+" + terms + "&" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.getBody());
        JsonNode name = root.get("items");
        List<BookDtoOut> books = new ArrayList<>();
        for (JsonNode node: name) {
            BookDtoOut bookDtoOut = new BookDtoOut();
            bookDtoOut.setId(node.path("id").asText());
            bookDtoOut.setTitle(node.path("volumeInfo").path("title").asText());

            List<AuthorDtoOut> authors = new ArrayList<>();
            JsonNode authorsField = node.path("volumeInfo").path("authors");
            for (JsonNode auhtorNode : authorsField) {
                AuthorDtoOut authorDtoOut = new AuthorDtoOut();
                authorDtoOut.setFirstName(auhtorNode.asText());
                authors.add(authorDtoOut);
            }
            bookDtoOut.setAuthors(new HashSet<>(authors));
            books.add(bookDtoOut);
        }
        return books;
    }
}
