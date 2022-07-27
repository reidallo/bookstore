package project.bookstore.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.bookstore.dto.OrderDto;
import project.bookstore.service.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping(value = "/orders/active")
    public ResponseEntity<List<OrderDto>> getAllActiveOrders() {
        return ResponseEntity.ok(orderService.getAllActiveOrders());
    }

    @GetMapping(value = "/orders/customer")
    public ResponseEntity<OrderDto> getAllOrdersOfACustomer(@RequestParam Long customerId) {
        return ResponseEntity.ok(orderService.getAllOrdersOfACustomer(customerId));
    }
}
