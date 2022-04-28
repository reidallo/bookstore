package project.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Book extends Base{

    private String title;
    private String description;
    private Double price;
    @ManyToMany
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "fk_book"), inverseJoinColumns = @JoinColumn(name = "fk_author"))
    private Set<Author> authors;
    @ManyToMany
    @JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "fk_book"), inverseJoinColumns = @JoinColumn(name = "fk_category"))
    private Set<Category> categories;

}
