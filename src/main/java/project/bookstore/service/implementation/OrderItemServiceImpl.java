package project.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookstore.dto.OrderItemDto;
import project.bookstore.mapper.OrderItemMapper;
import project.bookstore.model.Book;
import project.bookstore.model.Inventory;
import project.bookstore.model.OrderItem;
import project.bookstore.repository.BookRepository;
import project.bookstore.repository.InventoryRepository;
import project.bookstore.repository.OrderItemRepository;
import project.bookstore.service.OrderItemService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemDto> getOrderItem(Long orderId) {
        return orderItemRepository.findOrderItemByOrderId(orderId).stream().map(orderItemMapper::toDto)
                        .collect(Collectors.toList());
    }

    @Override
    public void addOrderItem(OrderItemDto orderItemDto) {
//        Optional<OrderItem> orderItemOptional = orderItemRepository.findOrderItemByOrderIdAndBookId(
//                orderItemDto.getOrderId(), orderItemDto.getBookId()
//        );
//        if (orderItemOptional.isPresent()) {
//            throw new IllegalStateException("You have already added this item to your order!");
//        }
        OrderItem orderItem = orderItemMapper.toEntity(orderItemDto);
        Book book = bookRepository.findBookById(orderItemDto.getBookId());
        Inventory inventory = inventoryRepository.findInventoryByBookId(orderItemDto.getBookId());
        if (inventory.getQuantity() <= 0) {
            throw new IllegalStateException("This book is out of stock!");
        }
        if (inventory.getQuantity() < orderItemDto.getQuantity()) {
            throw new IllegalStateException("There are only " + inventory.getQuantity() + " books left!");
        }
        Double price = book.getPrice()*orderItemDto.getQuantity();
        orderItem.setPrice(price);
        orderItemRepository.save(orderItem);
    }
}
