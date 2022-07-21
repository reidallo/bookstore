package project.bookstore.mapper.implementation;

import project.bookstore.dto.OrderItemDto;
import project.bookstore.mapper.OrderItemMapper;
import project.bookstore.model.OrderItem;

import java.util.List;

public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItem toEntity(OrderItemDto dto) {
        return null;
    }

    @Override
    public OrderItemDto toDto(OrderItem entity) {
        return null;
    }

    @Override
    public List<OrderItemDto> toDtoList(List<OrderItem> entityList) {
        return null;
    }
}
