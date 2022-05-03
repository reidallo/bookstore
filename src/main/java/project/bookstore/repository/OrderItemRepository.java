package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookstore.model.OrderItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findOrderItemByOrderId(Long orderId);
    Optional<OrderItem> findOrderItemByOrderIdAndBookId(Long orderId, Long bookId);
}
