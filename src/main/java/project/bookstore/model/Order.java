package project.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_book")
public class Order extends Base {

    private LocalDate date;
    private Double total;
    @ManyToOne
    @JoinColumn(name = "fk_customer", referencedColumnName = "id")
    private Customer customer;
}
