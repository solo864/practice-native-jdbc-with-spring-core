package dao.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import javax.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QPredicates {
    private final List<Predicate> predicates = new ArrayList<>();

    public static QPredicates builder(){
        return new QPredicates();
    }

    public <T> QPredicates add(T object, Function<T, Predicate> function ){
        if (object != null) {
            predicates.add(function.apply(object))
        }
        return this;
    }

    public List<Predicate> getPredicates(){
        return predicates;
    }
}
