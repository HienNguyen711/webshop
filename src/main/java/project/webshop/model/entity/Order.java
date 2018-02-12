package project.webshop.model.entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import project.webshop.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "orders")
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusType status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "order_product", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> orderProducts;

    public Order() {
    }
}
