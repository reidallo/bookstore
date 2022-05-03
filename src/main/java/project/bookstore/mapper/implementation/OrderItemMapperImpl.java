package project.bookstore.mapper.implementation;

import org.springframework.stereotype.Component;
import project.bookstore.dto.OrderItemDto;
import project.bookstore.mapper.OrderItemMapper;
import project.bookstore.model.Book;
import project.bookstore.model.Order;
import project.bookstore.model.OrderItem;

@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItem toEntity(OrderItemDto orderItemDto) {
        if (orderItemDto == null) {
            return null;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getOrderItemId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        Book book = new Book();
        book.setId(orderItemDto.getBookId());
        orderItem.setBook(book);
        if (orderItemDto.getOrderId() != null) {
            Order order = new Order();
            order.setId(orderItemDto.getOrderId());
            orderItem.setOrder(order);
        }
        return orderItem;
    }

    @Override
    public OrderItemDto toDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setOrderItemId(orderItem.getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setBookId(orderItem.getBook().getId());
        return orderItemDto;
    }
}
