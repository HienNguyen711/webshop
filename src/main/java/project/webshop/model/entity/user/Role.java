package project.webshop.model.entity.user;

import lombok.Getter;
import lombok.Setter;
import project.webshop.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "roles")
@Getter
@Setter
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(nullable = false, unique = true)
    @NotNull(message = "Roles can not be null")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }
}
