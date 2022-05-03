package project.bookstore.service;

import project.bookstore.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {

    List<OrderItemDto> getOrderItem(Long orderId);
    void addOrderItem(OrderItemDto orderItemDto);
}
