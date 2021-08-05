package ir.farbod.humanresource.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "person_schoolgrade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonSchoolGrade implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id_key")
//    private Long id;

    @Id
    @JsonIgnoreProperties("personSchoolGrades")
    @ManyToOne(
            targetEntity = Person.class,
            cascade = CascadeType.ALL,
            optional = false
    )
    private Person person;

    @Id
    @JsonIgnoreProperties("personSchoolGrades")
    @ManyToOne(
            targetEntity = SchoolGrade.class,
            cascade = CascadeType.ALL,
            optional = false
    )
    private SchoolGrade schoolGrade;

    @Id
    private LocalDate graduateDate = LocalDate.now();
}
