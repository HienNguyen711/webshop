package project.webshop.model.entity.payment;

import project.webshop.model.BaseEntity;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity containing data of the Credit Card.
 *
 * Cardinality is CreditCard(1-N) - User(1-N) = N:N
 */
@Entity(name = "creditcard")
@Table(name = "creditcard")
public class CreditCard extends BaseEntity {

}
