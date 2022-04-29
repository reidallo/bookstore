package project.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.bookstore.dto.AuthRequest;
import project.bookstore.dto.AuthResponse;
import project.bookstore.security.jwt.JwtUtil;

@Controller
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/bookstore/login")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex){
            throw new Exception("Invalid username/password!");
        }
        final String token = jwtUtil.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
