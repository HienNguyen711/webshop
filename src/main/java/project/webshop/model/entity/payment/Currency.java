package project.webshop.model.entity.payment;

import lombok.Data;
import project.webshop.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Currency extends BaseEntity{

    private String name;

    private String description;


}
