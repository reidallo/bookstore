package project.bookstore.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.bookstore.model.Customer;
import project.bookstore.model.Role;
import project.bookstore.model.User;
import project.bookstore.repository.CustomerRepository;
import project.bookstore.repository.RoleRepository;
import project.bookstore.repository.UserRepository;
import project.bookstore.security.request.RegisterRequest;
import project.bookstore.service.UserService;
import project.bookstore.statics.ERole;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("User with email: " + request.getEmail() + " already exists!");
        }
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalStateException("User with username: " + request.getUsername() + " already exists!");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .build();

        Role role = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                .orElseThrow(() -> new IllegalStateException("Role is not found!"));

        Set<Role> roles= new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .user(user)
                .build();
        customerRepository.save(customer);
    }
}
