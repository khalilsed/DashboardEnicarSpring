package tn.enicarthage.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table (name =  "T_ENSEIGNANT")
@Entity
public class Enseignant extends User implements Serializable {
	private static final long serialVersionUID = 1L;
    @Column( name = "ENSEI_TEL")
    String tel;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Groupe> groupes= new HashSet<Groupe>();
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Matiere mat;
    public Enseignant(){
    	super();
    };
}
