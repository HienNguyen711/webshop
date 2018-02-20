package project.webshop.model.entity.payment;

import lombok.Data;
import project.webshop.model.BaseEntity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;

/**
 * Entity containing data for type of Credit Card - this should be
 * implemented as Ref Data.
 *
 * Cardinality is CreditCardType(1-N)-CreditCard(1-1)
 *
 */
@Entity
@Table(name="creditcardtype")
@Data
public class CreditCardType extends BaseEntity {

    @Column(name = "NAME_TYPE", unique = true, nullable = false)
    private String nameType;

}
