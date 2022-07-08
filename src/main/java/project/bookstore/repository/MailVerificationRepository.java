package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookstore.model.MailVerificationToken;

import java.util.Optional;

@Repository
public interface MailVerificationRepository extends JpaRepository<MailVerificationToken, Long> {

    Optional<MailVerificationToken> findByToken(String token);
}
