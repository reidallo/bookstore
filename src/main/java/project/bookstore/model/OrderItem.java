package project.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double price;
    @OneToOne
    @JoinColumn(name = "fk_book", referencedColumnName = "id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "fk_order", referencedColumnName = "id")
    private Order order;

}
