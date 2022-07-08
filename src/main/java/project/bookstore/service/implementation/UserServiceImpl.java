package project.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import project.bookstore.dto.MailVerificationTokenDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.bookstore.model.Customer;
import project.bookstore.model.MailVerificationToken;
import project.bookstore.model.Role;
import project.bookstore.model.User;
import project.bookstore.repository.CustomerRepository;
import project.bookstore.repository.MailVerificationRepository;
import project.bookstore.repository.RoleRepository;
import project.bookstore.repository.UserRepository;
import project.bookstore.security.jwt.JwtUtils;
import project.bookstore.security.request.LoginRequest;
import project.bookstore.security.request.RegisterRequest;
import project.bookstore.security.response.JwtResponse;
import project.bookstore.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Value("${expiration}")
    private int expiration;
    private final MailVerificationRepository mailVerificationRepository;
    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(MailVerificationRepository mailVerificationRepository, JavaMailSender mailSender, UserRepository userRepository, RoleRepository roleRepository, CustomerRepository customerRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.mailVerificationRepository = mailVerificationRepository;
        this.mailSender = mailSender;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void register(RegisterRequest request, HttpServletRequest httpRequest) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("User with email: " + request.getEmail() + " already exists!");
        }
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalStateException("User with username: " + request.getUsername() + " already exists!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseThrow(() -> new IllegalStateException("Role is not found!"));

        Set<Role> roles= new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setUser(user);
        customerRepository.save(customer);

        MailVerificationTokenDto mailVerificationDto = new MailVerificationTokenDto(user, httpRequest.getContextPath());
        sendConfirmationEmail(mailVerificationDto);
    }

    @Override
    public void sendConfirmationEmail(MailVerificationTokenDto mailVerificationDto) {

        User user = mailVerificationDto.getUser();
        String token = UUID.randomUUID().toString();
        String url = "http://localhost:8080" + mailVerificationDto.getUrl() + "/confirmRegistration?token=" + token;

        MailVerificationToken mailVerificationToken = new MailVerificationToken();
        mailVerificationToken.setUser(user);
        mailVerificationToken.setToken(token);
        mailVerificationToken.setExpirationDate(calculateExpirationDate(expiration));

        mailVerificationRepository.save(mailVerificationToken);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Registration Confirmation");
            message.setText("Click the link to confirm your email: " + url);
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Date calculateExpirationDate(int expiration) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expiration);
        return new Date(calendar.getTime().getTime());
    }

    @Override
    public void confirmRegistration(String token) {

        MailVerificationToken mailVerificationToken = mailVerificationRepository.findByToken(token).orElseThrow(() ->
                new IllegalStateException("This token is not valid!"));

        Calendar calendar = Calendar.getInstance();
        if (mailVerificationToken.getExpirationDate().getTime() - calendar.getTime().getTime() <= 0) {
            throw new IllegalStateException("This token has expired!");
        }
        User user = userRepository.findByEmail(mailVerificationToken.getUser().getEmail()).orElseThrow(() ->
                new IllegalStateException("User not found"));
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public JwtResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);
        return new JwtResponse(token);
    }
}
