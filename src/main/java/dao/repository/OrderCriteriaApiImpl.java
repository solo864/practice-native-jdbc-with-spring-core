package dao.repository;

import dao.entity.Order;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderCriteriaApiImpl implements OrderCriteriaApi {

    private final EntityManager em;

    @Override
    public List<Order> findAll(Predicate predicate) {
        QPredicates predicate = QPredicates.builder().add("name", _Order.name).add("description");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = cb.createQuery(Order.class);
        criteria.where(predicate);
        return em.createQuery(criteria).getResultList();
    }
}
