package project.webshop.model.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import project.webshop.model.BaseEntity;

import javax.persistence.*;
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

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

    public Role() {
    }
}
