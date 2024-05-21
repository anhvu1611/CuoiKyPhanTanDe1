package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidates")
public class Candidate implements Serializable {
    @Id
    @Column(name = "candidate_id")
    private String id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    private String gender;
    private String email;
    private String phone;
    private String description;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    public Candidate(String id, String fullName, int yearOfBirth, String gender, String email, String phone, String description) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }
}
