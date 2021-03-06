package study.querydsl.test;

import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> findAllByFetchJoinOrderItems();
    List<Order> findAllByFetchJoinOrderItemsAndDeliveries();
}
