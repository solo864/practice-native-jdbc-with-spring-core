package service;

import dao.CustomerDao;
import dto.CustomerReadDto;
import java.util.List;
import mapper.CustomerMapper;
import model.filter.CustomerFilter;

public class CustomerService {

    private final CustomerDao customerDao;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerDao customerDao, CustomerMapper customerMapper) {
        this.customerDao = customerDao;
        this.customerMapper = customerMapper;
    }

    public List<CustomerReadDto> getAll() {
        return customerDao.findAll()
                .stream().map(customerMapper::mapModelToDto)
                .toList();
    }

    public List<CustomerReadDto> getAll(CustomerFilter filter) {
        return customerDao.findAll(filter)
                .stream()
                .map(customerMapper::mapModelToDto)
                .toList();
    }
}