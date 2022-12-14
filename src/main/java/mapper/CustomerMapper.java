package mapper;

import dto.CustomerReadDto;
import entity.Customer;

public class CustomerMapper {

    public CustomerReadDto mapModelToDto(Customer customer) {
        return CustomerReadDto.builder()
                .email(customer.getEmail())
                .firstname(customer.getFirstName())
                .build();
    }
}
