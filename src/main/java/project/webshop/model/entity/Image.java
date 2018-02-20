package project.webshop.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

//    @Column(name="full_image")
//    private String fullImagePath;
//
//    @Column(name="thumbnail_image")
//    private String thumbnailImagePath;


    @NotNull
    @Column(name = "image_url")
    private String imageUrl;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "product_id")
    private Product product;

    public Image() {
    }
}
