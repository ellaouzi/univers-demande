package com.fosagri.utils;

public enum StudentStringUitls {

	MAIN_MENU("MAIN MENU"),
	SHOW_ALL_STUDENTS("SHOW ALL STUDENTS"),
	FIRST_NAME("First Name"),
	LAST_NAME("Last Name"),
	GENDER("gender"),
	AGE("Age"),
	SAVE_BUTTON("Save"),
	CLEAR_BUTTON("Clear"), 
	UNIVERSITY("University");
	
	private final String string;
	
	private StudentStringUitls(String string) {
		this.string = string;
		
	}
	
	public String getString() {
		return this.string;
	}
}
