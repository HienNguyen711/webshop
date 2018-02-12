package project.webshop.model.entity;

import lombok.Getter;
import lombok.Setter;
import project.webshop.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "images")
@Getter
@Setter
@Table(name = "images")
public class Image extends BaseEntity {

    @Column(nullable = false)
    @NotNull(message = "Image cannot not be null")
    private String image;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Image() {
    }
}
