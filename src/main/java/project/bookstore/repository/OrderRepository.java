package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.bookstore.model.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o.* FROM bookstore.order_bookstore AS o LEFT JOIN bookstore.customer AS c ON o.fk_customer = c.id \n" +
            "LEFT JOIN bookstore.user AS u ON c.fk_user = u.id WHERE u.username LIKE :username AND o.active = true", nativeQuery = true)
    Optional<Order> findOrderByActiveAndCustomer(String username);

    @Query(value = "SELECT o.* FROM bookstore.order_bookstore AS o LEFT JOIN bookstore.order_item AS oi ON o.id = oi.fk_order \n" +
            "WHERE oi.id = :orderItemId", nativeQuery = true)
    Optional<Order> findOrderByOrderItemId(Long orderItemId);

    List<Order> findAllByActiveIsTrue();

    Order findByActiveIsTrueAndCustomer_Id(Long customerId);


}
