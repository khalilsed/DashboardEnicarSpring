package tn.enicarthage.entities;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table (name =  "T_ETUD_ETR")
public class Etudiant_etranger extends Etudiant implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column( name = "ETUD_PASS")
    int numPass;
	@Column( name = "ETUD_NAT")
    String nat;
	@OneToMany(cascade = CascadeType.REMOVE)
	   Set<Note> Notes=new HashSet<Note>();
	@OneToMany(cascade = CascadeType.REMOVE)
		Set<Absence> Absences=new HashSet<Absence>();
    public Etudiant_etranger() {
    }
}
