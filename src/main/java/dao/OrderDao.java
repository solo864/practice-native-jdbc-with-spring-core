package dao;


import entity.Order;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import util.ConnectionManager;

public class OrderDao {

    private static final String FIND_ALL_BY_CUSTOMER_ID = "SELECT closing_date, registration_date, status FROM orders WHERE customer_id = ?";

    @SneakyThrows
    public List<Order> findOrdersByCustomerId(Integer customerId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_CUSTOMER_ID)) {
            preparedStatement.setObject(1, customerId);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(CustomerDao.buildOrder(resultSet));
            }
            return orders;
        }
    }
}
