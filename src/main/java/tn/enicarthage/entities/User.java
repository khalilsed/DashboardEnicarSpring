package tn.enicarthage.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
@FieldDefaults(level = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)


@Entity
@Table (name =  "T_USER")
public abstract class User implements Serializable  {
	private static final long serialVersionUID = 1L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column( name = "USER_ID")
	 long id;
	 @Column( name = "USER_NAME")
	 String username;
	 @Column( name = "USER_PASSWORD")
	 String password;
	 @Column( name = "USER_NOM")
	 String nom;
	 @Column( name = "USER_PRE")
	 String prenom;
	 @Column( name = "USER_MAIL")
     String mail;
	 @Column( name = "USER_DATENAI")
     @Temporal(TemporalType.DATE)
	 Date dateNai;
	 
	 public User() {};
}
