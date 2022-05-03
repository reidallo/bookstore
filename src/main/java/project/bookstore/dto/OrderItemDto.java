package project.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    private Long orderItemId;
    private Integer quantity;
    private Double price;
    private Long bookId;
    private Long orderId;
}
