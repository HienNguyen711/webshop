package project.webshop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.webshop.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity(name = "categories")
@Getter
@Setter
@Table(name = "categories")
//@NoArgsConstructor
@AllArgsConstructor
//@SequenceGenerator(name = "seq", sequenceName = "category_seq")
public class Category extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @NotNull
    private String image;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Set<Category> children;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Product> products;

    public Category() {
    }
}
