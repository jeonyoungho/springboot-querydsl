package study.querydsl.test;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import study.querydsl.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

import static study.querydsl.test.QDelivery.delivery;
import static study.querydsl.test.QOrder.order;
import static study.querydsl.test.QOrderItem.orderItem;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Order> findAllByFetchJoinOrderItems() {
        return queryFactory.selectFrom(order)
                .distinct()
                .leftJoin(order.items, orderItem).fetchJoin()
                .fetch();
    }

    @Override
    public List<Order> findAllByFetchJoinOrderItemsAndDeliveries() {
        return queryFactory.selectFrom(order).distinct()
                .leftJoin(order.items, orderItem).fetchJoin()
                .leftJoin(order.deliveries, delivery).fetchJoin()
                .fetch();
    }
}
