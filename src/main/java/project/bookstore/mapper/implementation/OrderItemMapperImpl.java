package project.bookstore.mapper.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import project.bookstore.dto.OrderItemDto;
import project.bookstore.mapper.BookMapper;
import project.bookstore.mapper.OrderItemMapper;
import project.bookstore.model.OrderItem;

import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class OrderItemMapperImpl implements OrderItemMapper {

    private final BookMapper bookMapper;

    @Override
    public OrderItem toEntity(OrderItemDto dto) {
        return null;
    }

    @Override
    public OrderItemDto toDto(OrderItem entity) {

        if (entity == null)
            return null;

        OrderItemDto dto = new OrderItemDto();
        if (entity.getId() != null)
            dto.setId(entity.getId());
        if (entity.getDate() != null)
            dto.setDate(entity.getDate());
        if (entity.getQuantity() != null)
            dto.setQuantity(entity.getQuantity());
        if (entity.getBook() != null)
            dto.setBookDto(bookMapper.toDto(entity.getBook()));
        dto.setPrice(entity.getPrice());
        return dto;
    }

    @Override
    public List<OrderItemDto> toListDto(List<OrderItem> entityList) {

        if (entityList == null)
            return null;

        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
