package project.bookstore.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.bookstore.dto.BookDto;
import project.bookstore.service.OrderItemService;

@RestController
@AllArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping(value = "/order/item")
    public void addOrderItem(@RequestBody BookDto bookDto, @RequestParam Integer quantity) {
        orderItemService.addOrderItem(bookDto, quantity);
    }
}
