package project.webshop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import project.webshop.model.entity.payment.Currency;

import java.util.Date;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class OrderDto {

    private Long id;

    private String status;

    private Date date;

    private Set<ProductDto> products;

    private Currency currency;


    public OrderDto() {
    }
}
