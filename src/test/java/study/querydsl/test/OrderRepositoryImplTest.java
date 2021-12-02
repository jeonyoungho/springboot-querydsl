package study.querydsl.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
public class OrderRepositoryImplTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void findAllByFetchJoinOrderItemsTest() {
        // given
        Order order = Order.builder()
                .name("Order1")
                .build();

        order.addItem(new OrderItem("Item1"));
        order.addItem(new OrderItem("Item2"));
        order.addItem(new OrderItem("Item3"));

        orderRepository.save(order);

        em.flush();
        em.clear();

        // when
        List<Order> result = orderRepository.findAllByFetchJoinOrderItems();
        System.out.println("result.size() = " + result.size());

        for (Order order1 : result) {
            System.out.println("order1.getName() = " + order1.getName());
        }

        System.out.println(result.get(0).getItems().size() + "<- size:");

        List<OrderItem> order1 = result.get(0).getItems();
        for (OrderItem orderItem : order1) {
            System.out.println("orderItem.getProductName() = " + orderItem.getProductName());
        }

        // then
    }

    @Test
    @Rollback(value = false)
    public void findAllByFetchJoinOrderItemsAndDeliveriesTest() {
        // given
        Order order = Order.builder()
                .name("Order1")
                .build();

        order.addItem(new OrderItem("Item1"));
        order.addItem(new OrderItem("Item2"));
        order.addItem(new OrderItem("Item3"));

        order.addDelivery(new Delivery("Delivery1"));
        order.addDelivery(new Delivery("Delivery2"));
        order.addDelivery(new Delivery("Delivery3"));

        orderRepository.save(order);

        em.flush();
        em.clear();

        // when
        List<Order> result = orderRepository.findAllByFetchJoinOrderItemsAndDeliveries();
        System.out.println("result.size() = " + result.size());

        for (Order order1 : result) {

            List<OrderItem> items = order1.getItems();
            System.out.println("items size: " + items.size());
            for (OrderItem item : items) {
                System.out.println("item = " + item.getProductName());
            }

            Set<Delivery> deliveries = order1.getDeliveries();
            for (Delivery delivery : deliveries) {
                System.out.println("delivery = " + delivery.getAddress());
            }
        }

//        for (Order order1 : result) {
//            System.out.println("order1.getName() = " + order1.getName());
//        }
//
//        System.out.println(result.get(0).getItems().size() + "<- size:");
//
//        List<OrderItem> order1 = result.get(0).getItems();
//        for (OrderItem orderItem : order1) {
//            System.out.println("orderItem.getProductName() = " + orderItem.getProductName());
//        }


        // then
    }

}