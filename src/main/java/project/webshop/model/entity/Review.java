package project.webshop.model.entity;
import lombok.Getter;
import lombok.Setter;
import project.webshop.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "reviews")
@Getter
@Setter
@Table(name = "reviews")
public class Review extends BaseEntity{
    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    @NotNull(message = "Rating can not be null")
    private Integer stars;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Review() {
    }
}
