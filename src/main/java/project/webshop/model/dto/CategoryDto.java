package project.webshop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CategoryDto {

    private Long id;

    private String name;

    private String image;

    private Long parentId;

    private Set<CategoryDto> children;

    public CategoryDto() {
    }
}
