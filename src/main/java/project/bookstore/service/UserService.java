package project.bookstore.service;

import project.bookstore.security.request.LoginRequest;
import project.bookstore.security.request.RegisterRequest;
import project.bookstore.security.response.JwtResponse;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    void register(RegisterRequest request, HttpServletRequest httpRequest);

    void confirmRegistration(String token);

    JwtResponse login(LoginRequest request);
}
