package ir.farbod.humanresource.unit_test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schoolgrade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolGrade implements Serializable {

    @Id
    @Column(name = "id_key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;


    // @OneToMany(mappedBy = "schoolGrade", cascade = CascadeType.ALL)
    // @JsonManagedReference
    @OneToMany(mappedBy = "schoolGrade", cascade = CascadeType.ALL)
    //@JoinColumn(name = "schoolgrade_id_key")
    private List<PersonSchoolGrade> personSchoolGrades = new ArrayList<>();
}
