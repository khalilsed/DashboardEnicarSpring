package tn.enicarthage.entities;

import java.io.Serializable;

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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table (name =  "T_Resultat")
public class Resultat implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)// La génération de la clé primaire se fera à partir d’une Identité propre au SGBD
	@Column( name = "RESULTAT_ID")
	int id;
	@Column( name = "RESULTAT_MOY")
	float moy;
	@Column( name = "RESULTAT_DEC")
	@Enumerated(EnumType.STRING)
	Decision dec;
	@OneToOne
	private Etudiant etd;
	public Resultat() {};

}
