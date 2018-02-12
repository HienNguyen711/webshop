package project.webshop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ProductListDto {
    // List of product :id
    private Long id;

    public ProductListDto() {
    }
}
