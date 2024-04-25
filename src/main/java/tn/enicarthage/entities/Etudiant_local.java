package tn.enicarthage.entities;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tn.enicarthage.entities.Note;


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
@Table (name =  "T_ETUD_LOCAL")
public class Etudiant_local extends Etudiant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column( name = "ETUD_CIN")
	int cin;
	@javax.persistence.ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	   Set<Note> notes=new HashSet<Note>();
	@javax.persistence.ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	   Set<Absence> absences=new HashSet<Absence>();
	public Etudiant_local() {
		
	}
}
