package com.fosagri.utils;

public enum Choix {

	CHOIX1("Ifrane"),
	CHOIX2("Marrakech");

	private final String string;

	private Choix(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}
}
