package project.bookstore.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Integer postalCode;
    private String city;
    private String state;
    private String phone;
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
    @OneToOne
    @JoinColumn(name = "fk_user", referencedColumnName = "id")
    private User user;
}
