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
@Table(name = "reviews")
public class Reviews implements Serializable {

    private String comment;
    private int rating;
    @Id
    @ManyToOne
    @JoinColumn(name = "ISBN")
    private Book book;
    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
