package tn.enicarthage.entities;

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
@Table (name =  "T_NOTE")
public class Note {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)// La génération de la clé primaire se fera à partir d’une Identité propre au SGBD
	    @Column( name = "NOTE_ID")
		int id;
		@Column( name = "NOTE_TYPE")
		@Enumerated(EnumType.STRING)
		noteType type;
		@Column( name = "VNOTE")
		long vnote;
		@OneToOne
		private Matiere mat;
		public Note() {}
}	
