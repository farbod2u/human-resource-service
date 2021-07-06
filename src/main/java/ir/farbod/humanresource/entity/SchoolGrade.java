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
@Table(name = "schoolgrade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolGrade implements Serializable {

    @Id
    @Column(name = "id_key")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    // @OneToMany(mappedBy = "schoolGrade", cascade = CascadeType.ALL)
    // @JsonManagedReference
    @OneToMany(mappedBy = "schoolGrade", cascade = CascadeType.ALL)
    //@JoinColumn(name = "schoolgrade_id_key")
    private List<PersonSchoolGrade> personSchoolGrades = new ArrayList<>();
}
