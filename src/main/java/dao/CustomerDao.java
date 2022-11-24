package dao;

import entity.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import model.Gender;
import model.Role;
import util.ConnectionManager;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDao {

    private static final String FIND_ALL = "SELECT * FROM customer";

    @SneakyThrows
    private List<Customer> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(buildCustomer(resultSet));
            }
            return customers;
        }
    }

    private Customer buildCustomer(ResultSet resultSet) throws SQLException {
        return Customer.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .email(resultSet.getString("email"))
                .gender(Gender.valueOf(resultSet.getString("gender")))
                .role(Role.valueOf(resultSet.getString("role")))
                .build();
    }

}
