package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dao.CustomerDao;
import dto.CustomerReadDto;
import entity.Customer;
import java.util.List;
import java.util.stream.Stream;
import mapper.CustomerMapper;
import model.filter.CustomerFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerDao customerDao;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerService customerService;


    @Test
    void getAll() {
        List<Customer> customers = List.of(new Customer(1, "test", "test", "test"));
        List<CustomerReadDto> customerReadDtos = List.of(new CustomerReadDto("test", "test"));

        when(customerDao.findAll()).thenReturn(customers);
        when(customerMapper.mapModelToDto(customers.get(0))).thenReturn(customerReadDtos.get(0));

        List<CustomerReadDto> actualResult = customerService.getAll();
        assertThat(actualResult).hasSize(1);
        Stream<String> emails = actualResult.stream().map(CustomerReadDto::email);
        assertThat(emails).containsExactlyInAnyOrder("test");
    }

    @Test
    void getAllWithFiltering() {
        var customers = List.of(
                new Customer(1, "test", "test", "test"),
                new Customer(1, "test", "test", "test")
        );
        CustomerReadDto customerReadDto = new CustomerReadDto("test", "test");
        CustomerFilter filter = new CustomerFilter(null, null, null, null);

        when(customerDao.findAll(filter)).thenReturn(customers);
        when(customerMapper.mapModelToDto(customers.get(0))).thenReturn(customerReadDto);

        List<CustomerReadDto> actualResult = customerService.getAll(filter);
        assertThat(actualResult).hasSize(2);

        List<String> emails = actualResult.stream().map(CustomerReadDto::email).toList();
        assertThat(emails).containsExactlyInAnyOrder("test", "test");

    }

}
