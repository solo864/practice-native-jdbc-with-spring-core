package dao.repository;

import dao.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>, OrderCriteriaApi {
}
