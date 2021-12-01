package study.querydsl.test;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<OrderItem> items = new ArrayList<>();

    @Builder
    public Order(String name) {
        this.name = name;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }

}
