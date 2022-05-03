package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookstore.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findInventoryByBookId(Long bookId);
}
