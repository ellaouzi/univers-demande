package com.fosagri.utils;

public enum Periode {

	PERIODE1("DU 1 Au 6 Mars"),
	PERIODE2("DU 7 Au 12 Mars"),
	PERIODE3("DU 12 Au 18 Mars");

	private final String string;

	private Periode(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}
}
