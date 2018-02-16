package project.webshop.model.entity.user;

import lombok.Data;
import org.springframework.stereotype.Component;
import project.webshop.model.BaseEntity;

import javax.persistence.*;

@Entity(name = "organisation")
@Table(name = "organisation")
@Data
public class Organisation extends BaseEntity {

    @Column(name = "city")
    private String name;

    @Column(name = "address")
    private String address;


    @Column(name = "businessCode")
    private String businessCode;

    @Column(name = "sector")
    private String sector;

}
