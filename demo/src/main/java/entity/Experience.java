package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "experiences")
public class Experience implements Serializable {
    @Id
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;
    private String description;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @Id
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "position_id")
    @Id
    private Position position;
}
