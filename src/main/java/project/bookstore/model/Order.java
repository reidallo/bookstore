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
@Table(name = "order_bookstore")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;
    @ManyToOne
    @JoinColumn(name = "fk_customer", referencedColumnName = "id")
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;
    private boolean active;
}
