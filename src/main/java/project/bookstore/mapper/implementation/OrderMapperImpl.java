package project.bookstore.mapper.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import project.bookstore.dto.OrderDto;
import project.bookstore.mapper.CustomerMapper;
import project.bookstore.mapper.OrderItemMapper;
import project.bookstore.mapper.OrderMapper;
import project.bookstore.model.Order;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderMapperImpl implements OrderMapper {

    private final CustomerMapper customerMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    public Order toEntity(OrderDto dto) {
        return null;
    }

    @Override
    public OrderDto toDto(Order entity) {

        if (entity == null)
            return null;

        OrderDto dto = new OrderDto();
        if (entity.getId() != null)
            dto.setId(entity.getId());
        if (entity.getCustomer() != null)
            dto.setCustomerDto(customerMapper.toDto(entity.getCustomer()));
        if (entity.getOrderItems() != null)
            dto.setOrderItemDtoList(orderItemMapper.toListDto(entity.getOrderItems().stream().toList()));
        dto.setTotal(entity.getTotal());
        dto.setActive(entity.isActive());
        return dto;
    }

    @Override
    public List<OrderDto> toListDto(List<Order> entityList) {

        if (entityList == null)
            return null;

        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
