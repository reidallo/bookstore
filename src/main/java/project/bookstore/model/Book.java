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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double price;
    @ManyToMany
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "fk_book"), inverseJoinColumns = @JoinColumn(name = "fk_author"))
    private Set<Author> authors;
    @ManyToMany
    @JoinTable(name = "book_categories", joinColumns = @JoinColumn(name = "fk_book"), inverseJoinColumns = @JoinColumn(name = "fk_category"))
    private Set<Category> categories;
}
