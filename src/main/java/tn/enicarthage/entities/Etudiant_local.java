package tn.enicarthage.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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
	@OneToMany(cascade = CascadeType.REMOVE)
	   Set<Note> Notes=new HashSet<Note>();
	@ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	Set<Absence> absences=new HashSet<Absence>();
	public Etudiant_local() {
		
	}
}
