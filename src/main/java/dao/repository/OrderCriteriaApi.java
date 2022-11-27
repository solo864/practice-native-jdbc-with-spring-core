package dao.repository;

import dao.entity.Order;
import java.util.List;
import javax.persistence.criteria.Predicate;

public interface OrderCriteriaApi {
    List<Order> findAll(Predicate predicate);
}
