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
public class Customer extends Base{

    private String firstName;
    private String lastName;
    private String address;
    private Integer postalCode;
    private String city;
    private String state;
    private String phone;
    @OneToOne
    @JoinColumn(name = "fk_user", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

}
