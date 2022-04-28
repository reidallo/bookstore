package project.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class OrderItem extends Base{

    private Integer quantity;
    private Double price;
    @OneToOne
    @JoinColumn(name = "fk_book", referencedColumnName = "id")
    private Book book;
    @OneToOne
    @JoinColumn(name = "fk_order", referencedColumnName = "id")
    private Order order;
}
