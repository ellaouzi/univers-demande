package com.fosagri.utils;

public enum StringUtils {
	
	MENU_STUDENT("STUDENTS"),
	MENU_UNIVERSITY("UNIVERSITY"),
	MENU_ADHERENT("ADHERENT"),
	MENU_PRESTATION("PRESTATION"),
	MENU_ADD_STUDENT("Add Student"),
	MENU_ADD_ADHERENT("Add Adherent"),
	MENU_OPP_ADHERENT("Operations Adherents"),
	MENU_OPP_PRESTATION("Operations Prestations"),

	MENU_REMOVE_STUDENT("Remove Student"),
	MENU_CREATIONS("Operations");



	private final String string;
	
	private StringUtils(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}
}
