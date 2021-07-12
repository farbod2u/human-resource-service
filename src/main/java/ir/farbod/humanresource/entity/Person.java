package ir.farbod.humanresource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_key")
    private Long id;

    @Column(length = 200, nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(length = 10, nullable = false, unique = true)
    private String idNumber;

    //@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    //@JsonManagedReference
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
   // @JoinColumn(name = "person_id_key")
    private List<PersonSchoolGrade> personSchoolGrades = new ArrayList<>();
}
