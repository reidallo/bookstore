package project.bookstore.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.bookstore.security.request.RegisterRequest;
import project.bookstore.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/confirmRegistration")
    public void confirmRegistration(@RequestParam String token) {
        userService.confirmRegistration(token);
    }

    @PostMapping(value = "/register")
    public void registerUser(@Valid @RequestBody RegisterRequest request, HttpServletRequest httpRequest) {
        userService.register(request, httpRequest);
    }
}
