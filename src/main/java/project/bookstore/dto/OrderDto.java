package project.bookstore.dto;

import lombok.Getter;
import lombok.Setter;
import project.bookstore.model.Customer;

import java.util.List;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private Double total;
    private Customer customer;
    private List<OrderItemDto> orderItemDtoList;
}
