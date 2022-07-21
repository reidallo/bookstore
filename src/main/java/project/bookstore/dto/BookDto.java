package project.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

    private String id;
    private String title;
    private String description;
    private Double price;
    private String authors;
    private String categories;
}
