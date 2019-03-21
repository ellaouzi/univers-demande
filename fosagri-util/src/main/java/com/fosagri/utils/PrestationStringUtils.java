package com.fosagri.utils;

public enum PrestationStringUtils {

	PRESTATION_CHOIX1("Choix 1"),
	PRESTATION_PERIODE1("Période 1"),
	PRESTATION_CHOIX2("Choix 2"),
	PRESTATION_PERIODE2("Période 2"),
	PRESTATION_CODAG("PPR"),
	PRESTATION_CODAGCONJ("PPR Conj"),
	PRESTATION_GSM("Gsm"),
	PRESTATION_NOMBRE("Nombre debénéficiaires"),
	PRESTATION_EMAIL("Email"),
	PRESTATION_BENIFICIAIRE("BENIFICAIRE"),
	SAVE_BUTTON("Sauvegarder"),
	CLEAR_BUTTON("Retour");
   	private final String string;

	private PrestationStringUtils(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}

}
