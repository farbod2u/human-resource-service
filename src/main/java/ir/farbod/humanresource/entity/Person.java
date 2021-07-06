package ir.farbod.humanresource.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_key")
    private Long id;
    private String firstName;
    private String lastName;


    //@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    //@JsonManagedReference
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
   // @JoinColumn(name = "person_id_key")
    private List<PersonSchoolGrade> personSchoolGrades = new ArrayList<>();
}
