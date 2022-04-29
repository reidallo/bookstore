package project.bookstore.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
}
