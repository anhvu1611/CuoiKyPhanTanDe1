package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Book {
    @Id
    protected String ISBN;
    protected String name;
    @Column(name = "publish_year")
    protected int publishYear;
    @Column(name = "num_of_pages")
    protected int numberOfPages;
    protected double price;
    @ElementCollection
    @CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
    @Column(name = "author")
    protected Set<String> author;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    protected Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "reviews",
            joinColumns = @JoinColumn(name = "ISBN"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> persons;
    @OneToOne(mappedBy = "book")
    private BookTranslation bookTranslation;
}
