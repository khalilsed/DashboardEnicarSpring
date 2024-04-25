package tn.enicarthage.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table (name =  "T_MATIERE")
public class Matiere implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)// La génération de la clé primaire se fera à partir d’une Identité propre au SGBD
	@Column( name = "MATIERE_ID")
	int id;
	@Column( name = "MATIERE_NOM")
	String nom;
	@Column( name = "MATIERE_coef")
	float coef;
	@OneToMany( cascade =CascadeType.ALL, mappedBy = "mat")
	@JsonIgnore
	Set<Enseignant> enseignants=new HashSet<Enseignant>();
	
	
	public Matiere(){};
	
}
