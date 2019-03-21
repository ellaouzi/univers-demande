package com.fosagri.utils;

public enum AdherentStringUtils {

	ADHERENT_NOM("Nom"),
	ADHERENT_PRENOM("Prenom"),
	ADHERENT_POSITION("Prenom"),
	ADHERENT_AGE("AGE"),
	ADHERENT_PPR("PPR"),
	GENDER("gender"),
	AGE("Age"),
	SAVE_BUTTON("Save"),
	CLEAR_BUTTON("Clear"),
	UNIVERSITY("University");
  	private final String string;

	private AdherentStringUtils(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}

}
