package project.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private Double total;
    private CustomerDto customerDto;
    private List<OrderItemDto> orderItemDtoList;
    private boolean active;
}
