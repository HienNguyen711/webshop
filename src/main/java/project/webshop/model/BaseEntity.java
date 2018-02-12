package project.webshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Version
//    private Long version;
}
