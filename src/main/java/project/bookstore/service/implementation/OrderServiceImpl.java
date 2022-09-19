package project.bookstore.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.bookstore.dto.OrderDto;
import project.bookstore.mapper.OrderMapper;
import project.bookstore.repository.OrderRepository;
import project.bookstore.service.OrderService;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderMapper.toListDto(orderRepository.findAll());
    }

    @Override
    public List<OrderDto> getAllActiveOrders() {
        return orderMapper.toListDto(orderRepository.findAllByActiveIsTrue());
    }

    //A customer should have only one active order
    @Override
    public OrderDto getActiveOrderOfACustomer(Long customerId) {
        return orderMapper.toDto(orderRepository.findByActiveIsTrueAndCustomer_Id(customerId));
    }
}
