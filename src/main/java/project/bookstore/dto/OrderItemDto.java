package project.bookstore.dto;

import lombok.Getter;
import lombok.Setter;
import project.bookstore.model.Order;

import java.util.Date;

@Getter
@Setter
public class OrderItemDto {

    private Long id;
    private Date date;
    private Integer quantity;
    private Double price;
    private BookDto bookDto;
    private Order order;
}
