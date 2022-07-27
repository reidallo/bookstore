package project.bookstore.mapper.implementation;

import org.springframework.stereotype.Component;
import project.bookstore.dto.CustomerDto;
import project.bookstore.mapper.CustomerMapper;
import project.bookstore.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerDto dto) {
        return null;
    }

    @Override
    public CustomerDto toDto(Customer entity) {

        if (entity == null)
            return null;

        CustomerDto dto = new CustomerDto();
        if (entity.getId() != null)
            dto.setId(entity.getId());
        if (entity.getFirstName() != null)
            dto.setFirstName(entity.getFirstName());
        if (entity.getLastName() != null)
            dto.setLastName(entity.getLastName());
        if (entity.getAddress() != null)
            dto.setAddress(entity.getAddress());
        if (entity.getCity() != null)
            dto.setCity(entity.getCity());
        if (entity.getState() != null)
            dto.setState(entity.getState());
        if (entity.getPhone() != null)
            dto.setPhone(entity.getPhone());
        if (entity.getPostalCode() != null)
            dto.setPostalCode(entity.getPostalCode());
        return dto;
    }

    @Override
    public List<CustomerDto> toListDto(List<Customer> entityList) {

        if (entityList == null)
            return null;

        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
