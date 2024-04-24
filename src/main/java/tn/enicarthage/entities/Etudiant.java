package tn.enicarthage.entities;


import java.io.Serializable;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import javax.persistence.OneToOne;

import org.jetbrains.annotations.Nullable;

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
@FieldDefaults(level = AccessLevel.PROTECTED)

@Entity
public abstract class  Etudiant extends User implements Serializable {
   private static final long serialVersionUID = 1L;
   @Column( name = "ETUD_ADRESSE")
   @Embedded//pour embarquer l'objet adresse
   @Nullable
   Adresse adr;
   @Column( name = "ETUD_SEXE")
   @Enumerated(EnumType.STRING)
   @Nullable
   Sexe sexe;
   @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   Groupe grp;
   @OneToOne
   Resultat res;
   
   
   public Etudiant() {
	  Adresse a=new Adresse();
	  this.setAdr(a);
	}

}
