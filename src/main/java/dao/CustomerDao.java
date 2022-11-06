package dao;

import entity.Customer;
import entity.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import model.enums.Status;
import model.filter.CustomerFilter;
import util.ConnectionManager;

@RequiredArgsConstructor
public class CustomerDao {

    private final OrderDao orderDao;

    private static final String FIND_ALL_SQL = "SELECT * FROM customer";
    private static final String FIND_ALL_CUSTOMER_WITH_ORDERS = """
                        
            SELECT 
            c.id,
            c.first_name, 
            c.password,
            c.email,
            o.status,
            o.closing_date,
            o.registration_date
            FROM customer c
            INNER JOIN orders o on c.id = o.customer_id
            WHERE c.id = ?           """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id =?";

    @SneakyThrows
    public List<Customer> findAllWithOrders(Integer customerId) {
        try (var connection = ConnectionManager.get(); var preparedStatement = connection.prepareStatement(FIND_ALL_CUSTOMER_WITH_ORDERS)) {
            preparedStatement.setObject(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            List<Customer> customers = new ArrayList<>();
            List<Order> orders = new ArrayList<>();
            if (resultSet.next()) {
                orders.add(buildOrder(resultSet));
                customers.add(
                        Customer.builder().id(resultSet.getInt("id")).firstName(resultSet.getString("first_name")).email(resultSet.getString("email"))
                                .password(resultSet.getString("password")).orders(orders).build());
            }
            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return customers;
        }
    }

    @SneakyThrows
    public Optional<Customer> findByIdWithAllOrders(Integer customerId) {
        List<Order> orders = orderDao.findOrdersByCustomerId(customerId);
        try (var connection = ConnectionManager.get(); var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, customerId);
            System.out.println(preparedStatement);
            var resultSet = preparedStatement.executeQuery();
            Customer customersWithOrder = null;
            if (resultSet.next()) {
                customersWithOrder = buildCustomer(resultSet);
            }
            customersWithOrder.setOrders(orders);
            return Optional.ofNullable(customersWithOrder);
        }
    }


    @SneakyThrows
    public List<Customer> findAll() {
        try (var connection = ConnectionManager.get(); var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(buildCustomer(resultSet));
            }
            return customers;
        }
    }

    @SneakyThrows
    public List<Customer> findAll(CustomerFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.email() != null) {
            whereSql.add(" email LIKE ?");
            parameters.add("%" + filter.email() + "%");
        }
        if (filter.firstname() != null) {
            whereSql.add(" first_name LIKE ?");
            parameters.add("%" + filter.firstname() + "%");
        }
        parameters.add(filter.limit());
        parameters.add(filter.offset());
        System.out.println(parameters);
        var where = whereSql.stream().collect(Collectors.joining(" AND ", " WHERE ", " LIMIT ? OFFSET ? "));
        var sql = whereSql.isEmpty() ? FIND_ALL_SQL + " LIMIT ? OFFSET ? " : FIND_ALL_SQL + where;
        System.out.println(sql);
        try (var connection = ConnectionManager.get(); var preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customer = new ArrayList<>();
            while (resultSet.next()) {
                customer.add(buildCustomer(resultSet));
            }
            return customer;
        }
    }

    private Customer buildCustomer(ResultSet resultSet) throws SQLException {
        return Customer.builder().id(resultSet.getInt("id")).email(resultSet.getString("email")).password(resultSet.getString("password"))
                .firstName(resultSet.getString("first_name")).build();
    }


    public static Order buildOrder(ResultSet resultSet) throws SQLException {
        return Order.builder().status(Status.valueOf(resultSet.getString("status"))).closingDate(resultSet.getDate("closing_date").toLocalDate())
                .registrationDate(resultSet.getDate("registration_date").toLocalDate()).build();
    }
}