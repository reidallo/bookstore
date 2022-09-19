package project.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private Integer quantity;
    private double price;
    @OneToOne
    @JoinColumn(name = "fk_book", referencedColumnName = "id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "fk_order", referencedColumnName = "id")
    @JsonIgnore
    private Order order;
}
