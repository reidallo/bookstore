package project.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookstore_user")
public class User extends Base{

    private String username;
    private String password;
    @ManyToOne()
    @JoinColumn(name = "fk_role", referencedColumnName = "id")
    private Role role;
}
