package project.bookstore.service;

import project.bookstore.dto.BookDto;

public interface OrderItemService {

    void addOrderItem(BookDto bookDto, Integer quantity);

    void removeOrderItem(Long orderItemId);
}
