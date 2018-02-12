package project.webshop.model.entity;
import lombok.Getter;
import lombok.Setter;
import project.webshop.model.BaseEntity;
import project.webshop.model.entity.user.User;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "products")
@Getter
@Setter
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false)
    @NotNull(message = "Product name can not be null")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Product description cannot be null")
    private String description;

    @Column(nullable = false)
//    @Digits(integer = 10 /*precision*/, fraction = 2 /*scale*/)
//    @DecimalMin(value = "1.00", message = "{campaign.donationMinimum.decimalMin}")
    @DecimalMin(value = "1.00", message = "Min price of product cannot be under 1.00")
    private Double price;

    @Column(nullable = false)
    private String image;

    @Column
    private String color;

    @Column
    private String country;

    @Column
    private String code;

    @Column
    private String brand;

    @Column
    private String sizes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Image> productImages;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", orphanRemoval = true)
    private Set<Review> productReviews;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "wishProducts")
    private Set<User> wishUsers;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "cartProducts")
    private Set<User> cartUsers;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "orderProducts")
    private Set<Order> orders;

    public Product() {
    }




}
