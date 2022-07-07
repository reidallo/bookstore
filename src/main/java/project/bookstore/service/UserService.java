package project.bookstore.service;

import project.bookstore.security.request.RegisterRequest;

public interface UserService {

    void register(RegisterRequest request);
}
