package project.webshop.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ApiModel(value = "Images")
public class ImageDto {
    private Long id;

    private String image;

    public ImageDto() {
    }
}
