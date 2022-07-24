package project.bookstore.logged;

import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedUser {

    public static String loggedInUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
