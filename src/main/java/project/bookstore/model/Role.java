package project.bookstore.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookstore_role")
public class Role extends Base{

    private String name;
    @OneToMany(mappedBy = "role")
    private Set<User> users;
}
