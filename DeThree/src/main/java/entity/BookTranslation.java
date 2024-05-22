package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "book_translations")
public class BookTranslation extends Book implements Serializable {
    @Column(name = "translate_name", columnDefinition = "nvarchar(255)")
    private String translateName;
    private String language;
    @OneToOne
    @JoinColumn(name = "ISBN")
    private Book book;
}
