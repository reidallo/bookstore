package project.bookstore.service.implementation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.bookstore.dto.BookDtoOut;
import project.bookstore.service.BookService;

import java.io.IOException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Override
    public List<BookDtoOut> getBookByTitle(String search, String terms) throws IOException {

        String url = "https://www.googleapis.com/books/v1/volumes?q=" + search + "+" + terms;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree((JsonParser) response.getBody());

        return null;
    }
}
