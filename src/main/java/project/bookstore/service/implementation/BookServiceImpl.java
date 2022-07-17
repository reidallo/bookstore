package project.bookstore.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.bookstore.dto.AuthorDtoOut;
import project.bookstore.dto.BookDtoOut;
import project.bookstore.dto.CategoriesDtoOut;
import project.bookstore.service.BookService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Value("${EXTERNAL_API}")
    private String EXTERNAL_API;
    @Value("${API_KEY}")
    private String API_KEY;

    @Override
    public List<BookDtoOut> getBooks(String name, String searchBy, String terms) throws IOException {

        //external api
        //https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&key=yourAPIKey
        String url = EXTERNAL_API + "?q=" + name + "+" + searchBy +":" + terms + "&key=" + API_KEY ;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.getBody());
        JsonNode volume = root.get("items");
        List<BookDtoOut> books = new ArrayList<>();
        for (JsonNode volumeItem: volume) {
            BookDtoOut bookDtoOut = convertFromJsonToDto(volumeItem);
            books.add(bookDtoOut);
        }
        return books;
    }

    @Override
    public BookDtoOut getBookById(String id) throws JsonProcessingException {

        String url = EXTERNAL_API + "/" + id + "?key=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.getBody());

        return convertFromJsonToDto(root);
    }

    //to minimize duplicated code
    private BookDtoOut convertFromJsonToDto(JsonNode jsonNode) {

        BookDtoOut bookDtoOut = new BookDtoOut();
        bookDtoOut.setId(jsonNode.path("id").asText());
        bookDtoOut.setTitle(jsonNode.path("volumeInfo").path("title").asText());
        bookDtoOut.setDescription(jsonNode.path("volumeInfo").path("description").asText());
        //check if book is for sale
        if (jsonNode.path("saleInfo").path("saleability").textValue().equals("FOR_SALE")) {
            bookDtoOut.setPrice(jsonNode.path("saleInfo").path("listPrice").path("amount").asDouble());
        }

        JsonNode authorsField = jsonNode.path("volumeInfo").path("authors");
        List<AuthorDtoOut> authors = new ArrayList<>();
        for (JsonNode authorNode : authorsField) {
            AuthorDtoOut authorDtoOut = new AuthorDtoOut();
            authorDtoOut.setFirstName(authorNode.asText() != null ? authorNode.asText().split(" ")[0] : null);
            //check if author has middle name
            if (authorNode.asText().split(" ").length > 1) {
                if (authorNode.asText().split(" ").length == 2) {
                    authorDtoOut.setLastName(authorNode.asText().split(" ")[1]);
                } else {
                    authorDtoOut.setMiddleName(authorNode.asText().split(" ")[1]);
                    authorDtoOut.setLastName(authorNode.asText().split(" ")[2]);
                }
            }
            authors.add(authorDtoOut);
        }

        JsonNode categoriesField = jsonNode.path("volumeInfo").path("categories");
        List<CategoriesDtoOut> categories = new ArrayList<>();
        for (JsonNode categoriesNode : categoriesField) {
            CategoriesDtoOut categoriesDtoOut = new CategoriesDtoOut();
            categoriesDtoOut.setName(categoriesNode.asText());
            categories.add(categoriesDtoOut);
        }

        bookDtoOut.setAuthors(new HashSet<>(authors));
        bookDtoOut.setCategories(new HashSet<>(categories));
        return bookDtoOut;
    }
}
