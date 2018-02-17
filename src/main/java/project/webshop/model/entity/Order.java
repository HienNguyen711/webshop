package project.webshop.model.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import project.webshop.model.BaseEntity;
import project.webshop.model.entity.user.User;

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
    @JsonProperty("status_type")
    private StatusType status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")// json format
    private Date date;

    //jsonignore

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "order_product", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> orderProducts;

    public Order() {
    }
}
