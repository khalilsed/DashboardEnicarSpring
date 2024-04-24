package tn.enicarthage.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table (name =  "T_GROUPE")
public class Groupe {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)// La génération de la clé primaire se fera à partir d’une Identité propre au SGBD
	 @Column( name = "GROUPE_ID")
     long id;
	 @Column( name = "GROUPE_NOM")
     String nom;
	 @Column( name = "GROUP_NIVEAU")
	 @Enumerated(EnumType.STRING)
     Niveau niveau;
	 @ManyToMany(cascade = CascadeType.ALL)
	 private Set<Matiere> matieres= new HashSet<Matiere>();
	 @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	 private Specialite spec;
	 
	 public Groupe(){};
}
