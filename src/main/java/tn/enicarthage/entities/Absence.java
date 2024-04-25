package tn.enicarthage.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)// La génération de la clé primaire se fera à partir d’une Identité propre au SGBD
    @Column( name = "ABSENCE_ID")
	int id;
	@Column( name = "ABSENCE_DATE") 
	Date date;
	@OneToOne
	private Matiere mat;
	@javax.persistence.ManyToMany(mappedBy = "absences", cascade = javax.persistence.CascadeType.ALL)
    private Set<Etudiant_local> etudiants = new HashSet<>();
	public Absence() {}

}