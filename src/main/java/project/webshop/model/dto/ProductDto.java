package project.webshop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ProductDto {
    private Long id;
    @NotNull
    private String name;
    private Double price;
    private String description;
    private String image;
    private String color;
    private String country;
    private String code;
    private String brand;
    private Boolean isFavorite;
    private Set<String> sizes;
    private Set<ReviewDto> reviews;
    private Set<ImageDto> images;
    private CategoryDto category;

    public ProductDto() {
    }
}
