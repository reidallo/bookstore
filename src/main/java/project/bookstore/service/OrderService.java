package project.bookstore.service;

import project.bookstore.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders();

    List<OrderDto> getAllActiveOrders();

    OrderDto getActiveOrderOfACustomer(Long customerId);
}
