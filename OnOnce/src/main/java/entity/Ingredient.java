package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {
    @Id
    @Column(name = "ingredient_id")
    private String id;
    @Column(name = "ingredient_name")
    private String name;
    private String unit;
    private double price;
    private double quantity;
    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    @Column(name = "supplier_name")
    private String suplierName;

    @ManyToMany(mappedBy = "ingredients")
    private List<Item> items = new ArrayList<>();
}
