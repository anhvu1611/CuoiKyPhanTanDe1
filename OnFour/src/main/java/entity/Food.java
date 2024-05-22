package entity;

import enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "foods")
public class Food extends Item implements Serializable {
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "preparation_time")
    private int preparationTime;
    @Column(name = "serving_time")
    private int servingTime;

    public Food(String id,Type type, int preparationTime, int servingTime) {
        super(id);
        this.type = type;
        this.preparationTime = preparationTime;
        this.servingTime = servingTime;
    }

    public Type getType() {
        return type;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public int getServingTime() {
        return servingTime;
    }

    public Food() {
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setServingTime(int servingTime) {
        this.servingTime = servingTime;
    }
}
