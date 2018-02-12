package project.webshop.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ImageDto {
    private Long id;

    private String image;

    public ImageDto() {
    }
}
