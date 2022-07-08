package project.bookstore.service;

import project.bookstore.dto.MailVerificationTokenDto;
import project.bookstore.security.request.LoginRequest;
import project.bookstore.security.request.RegisterRequest;
import project.bookstore.security.response.JwtResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface UserService {

    void register(RegisterRequest request, HttpServletRequest httpRequest);

    void sendConfirmationEmail(MailVerificationTokenDto mailVerificationDto);

    Date calculateExpirationDate(int expiration);

    void confirmRegistration(String token);

    JwtResponse login(LoginRequest request);
}
