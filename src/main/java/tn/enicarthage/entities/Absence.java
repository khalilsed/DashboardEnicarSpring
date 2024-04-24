package tn.enicarthage.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table (name =  "T_ABSENCE")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ABSENCE_ID")
    int id;
    @Column( name = "ABSENCE_DATE")
    Date date;
    @OneToOne
    private Matiere mat;
    @ManyToMany(mappedBy = "absences", cascade = CascadeType.ALL)
    private Set<Etudiant_local> etudiants = new HashSet<>();
    public Absence() {}
}
