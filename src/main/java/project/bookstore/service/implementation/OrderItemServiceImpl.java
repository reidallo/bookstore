package project.bookstore.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.bookstore.dto.BookDto;
import project.bookstore.logged.LoggedUser;
import project.bookstore.mapper.BookMapper;
import project.bookstore.model.Book;
import project.bookstore.model.Customer;
import project.bookstore.model.Order;
import project.bookstore.model.OrderItem;
import project.bookstore.repository.BookRepository;
import project.bookstore.repository.CustomerRepository;
import project.bookstore.repository.OrderItemRepository;
import project.bookstore.repository.OrderRepository;
import project.bookstore.service.OrderItemService;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    //recommended over "Autowired" type of injection
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public void addOrderItem(BookDto bookDto, Integer quantity) {

        if (bookDto.getPrice() == null)
            throw new IllegalStateException("Sorry! This book is not for sale!");

        Book book = bookMapper.toEntity(bookDto);

        if (bookRepository.findById(book.getId()).isEmpty())
            bookRepository.save(book);

        OrderItem orderItem = new OrderItem();
        orderItem.setBook(book);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(book.getPrice()*quantity);
        orderItem.setDate(new Date());

        Order order = new Order();
        Optional<Order> orderOptional = orderRepository.findOrderByActiveAndCustomer(LoggedUser.loggedInUser());

        //check if this customer has already an active order (to add items into)
        if (orderOptional.isEmpty()) {

            Customer customer = customerRepository.findCustomerByUser(LoggedUser.loggedInUser()).orElseThrow(() ->
                    new IllegalStateException("You are not authorised!"));
            order.setCustomer(customer);

            //later, when this customer purchase this order, active will be set to false
            order.setActive(true);
            order.setTotal(order.getTotal() + orderItem.getPrice());
            orderRepository.save(order);

            Set<Order> orderSet = new HashSet<>();
            if (customer.getOrders() != null)
                orderSet = customer.getOrders();
            orderSet.add(order);
            customer.setOrders(orderSet);
        } else {
            order = orderOptional.get();
            order.setTotal(order.getTotal() + orderItem.getPrice());
        }

        orderItem.setOrder(order);
        orderItemRepository.save(orderItem);

        Set<OrderItem> orderItemSet = new HashSet<>();
        if (order.getOrderItems() != null)
                orderItemSet = order.getOrderItems();
        orderItemSet.add(orderItem);
        order.setOrderItems(orderItemSet);
    }

    @Override
    public void removeOrderItem(Long id) {

        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("This order item does not exist!"));
        orderItemRepository.delete(orderItem);
    }
}
