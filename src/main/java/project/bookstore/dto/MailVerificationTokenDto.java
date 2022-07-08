package project.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import project.bookstore.model.User;

@Getter
@Setter
@AllArgsConstructor
public class MailVerificationTokenDto {

    private User user;
    private String url;
}
