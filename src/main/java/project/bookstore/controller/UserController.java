package project.bookstore.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.bookstore.security.request.RegisterRequest;
import project.bookstore.service.UserService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/register")
    public void registerUser(@Valid @RequestBody RegisterRequest request) {
        userService.register(request);
    }
}
