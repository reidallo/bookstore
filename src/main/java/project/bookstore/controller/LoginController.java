package project.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.bookstore.dto.AuthRequest;
import project.bookstore.dto.AuthResponse;
import project.bookstore.security.jwt.JwtUtil;
import project.bookstore.service.implementation.UserDetailsServiceImpl;

@RestController
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/bookstore/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails.getUsername())));
//            return ResponseEntity.ok(authentication);
        } catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
