package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
//@ToString
@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item implements Serializable {
    @Id
    private String id;
    private String name;
    private double price;
    private String description;
    @Column(name = "on_special")
    private boolean onSpecial;

    @ManyToMany
    @JoinTable(
            name = "items_ingredients",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients = new ArrayList<>();

    public Item(String id) {
        this.id = id;
    }


}
