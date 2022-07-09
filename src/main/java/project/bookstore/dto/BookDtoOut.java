package project.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BookDtoOut {

    private String id;
    private String title;
    private String description;
    private Set<AuthorDtoOut> authors;
    private Set<CategoriesDtoOut> categories;
    private Double price;
}
