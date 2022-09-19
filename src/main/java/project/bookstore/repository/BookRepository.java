package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookstore.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
