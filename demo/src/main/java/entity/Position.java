package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "positions")
public class Position implements Serializable {
    @Id
    @Column(name = "position_id")
    private String id;
    private String name;
    private String description;
    private double salary;
    private int number;

    @OneToMany(mappedBy = "position")
    private List<Candidate> candidates;

    public Position(String id, String name, String description, double salary, int number) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.salary = salary;
        this.number = number;
    }
}
