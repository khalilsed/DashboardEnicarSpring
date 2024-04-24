package tn.enicarthage.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Table (name =  "T_SPECIALITE")
public class Specialite implements Serializable {
	 private static final long serialVersionUID = 1L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)// La génération de la clé primaire se fera à partir d’une Identité propre au SGBD
	 @Column( name = "SPEC_ID")
	 int id;
	 @Column( name = "SPEC_NOM")
	 String nom;
	 
	 public Specialite(){}	 
	 
}
