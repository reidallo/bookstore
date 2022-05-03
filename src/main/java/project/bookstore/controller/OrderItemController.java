package project.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bookstore.dto.OrderItemDto;
import project.bookstore.service.OrderItemService;

import java.util.List;

@RestController
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping(value = "/bookstore/order/item")
    public ResponseEntity<List<OrderItemDto>> getOrderItem(@RequestParam Long orderId) {
        return ResponseEntity.ok(orderItemService.getOrderItem(orderId));
    }

    @PostMapping(value = "/bookstore/order/item")
    public void addOrderItem(@RequestBody OrderItemDto orderItemDto) {
        orderItemService.addOrderItem(orderItemDto);
    }
}
