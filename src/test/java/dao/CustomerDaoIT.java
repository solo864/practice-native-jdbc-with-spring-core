package dao;


import entity.Customer;
import java.util.List;
import model.filter.CustomerFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerDaoIT {

    private  CustomerDao customerDao  = new CustomerDao();

    @Test
    void findAll() {
        List<Customer> actualResult = customerDao.findAll();
        Assertions.assertThat(actualResult).hasSize(3);

        List<String> names = actualResult.stream().map(Customer::getFirstname).toList();
        Assertions.assertThat(names).containsExactlyInAnyOrder("Findlay", "Cleveland", "Isobelle");
    }

    @Test
    void findAllWithFiltering() {
        CustomerFilter emptyFilter = CustomerFilter.builder().build();

        List<Customer> actualResult = customerDao.findAll();
        Assertions.assertThat(actualResult).hasSize(3);

        List<String> names = actualResult.stream().map(Customer::getFirstname).toList();
        Assertions.assertThat(names).containsExactlyInAnyOrder("Findlay", "Cleveland", "Isobelle");
    }

}
