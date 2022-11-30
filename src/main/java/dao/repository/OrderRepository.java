package dao.repository;

import com.querydsl.core.types.Predicate;
import dao.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository implements JpaRepository<Order, Integer> {

    List<Order> findAll(Predicate predicate);
}
