package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "publishers")
public class Publisher implements Serializable {
    @Id
    @Column(name = "publisher_id")
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
}
