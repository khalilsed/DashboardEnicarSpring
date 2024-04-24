package tn.enicarthage.entities;


import org.jetbrains.annotations.Nullable;

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
public class Adresse {
	@Nullable
    int adr_num;
	@Nullable
	String adr_rue;
	@Nullable
    String adr_ville;
	
	int codePost;
	public Adresse() {}
}
