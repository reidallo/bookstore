package project.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.bookstore.model.User;
import project.bookstore.repository.UserRepository;
import project.bookstore.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new UserDetailsImpl(user);
    }

    public User getUser() throws UsernameNotFoundException {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    public static boolean isAdmin(Authentication authentication){
        return authentication.getAuthorities().stream().allMatch(
                role-> role.getAuthority().equals("Admin"));
    }
}

