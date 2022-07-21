package project.bookstore.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.bookstore.dto.BookDto;
import project.bookstore.service.BookService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Value("${EXTERNAL_API}")
    private String EXTERNAL_API;
    @Value("${API_KEY}")
    private String API_KEY;

    @Override
    public List<BookDto> getBooks(String name, String searchBy, String terms) throws IOException {

        //external api
        //https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&key=yourAPIKey
        String url = EXTERNAL_API + "?q=" + name + "+" + searchBy +":" + terms + "&key=" + API_KEY ;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.getBody());
        JsonNode volume = root.get("items");
        List<BookDto> books = new ArrayList<>();
        for (JsonNode volumeItem: volume) {
            BookDto bookDto = convertFromJsonToDto(volumeItem);
            books.add(bookDto);
        }
        return books;
    }

    @Override
    public BookDto getBookById(String id) throws JsonProcessingException {

        String url = EXTERNAL_API + "/" + id + "?key=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.getBody());

        return convertFromJsonToDto(root);
    }

    //to minimize duplicated code
    private BookDto convertFromJsonToDto(JsonNode jsonNode) {

        BookDto bookDto = new BookDto();
        bookDto.setId(jsonNode.path("id").asText());
        bookDto.setTitle(jsonNode.path("volumeInfo").path("title").asText());
        bookDto.setDescription(jsonNode.path("volumeInfo").path("description").asText());
        //check if book is for sale
        if (jsonNode.path("saleInfo").path("saleability").textValue().equals("FOR_SALE")) {
            bookDto.setPrice(jsonNode.path("saleInfo").path("listPrice").path("amount").asDouble());
        }
        bookDto.setAuthors(jsonNode.path("volumeInfo").path("authors").toString());
        bookDto.setCategories(jsonNode.path("volumeInfo").path("categories").toString());

        return bookDto;
    }
}
