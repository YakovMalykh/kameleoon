package com.kameleoon.quote.models;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer pros;
    private Integer cons;

    @Column(name = "creation_date")
    private LocalDate creationDate;
    @ManyToOne
    @JoinColumn(name = "voting_id")
    private Voting voting;

}
