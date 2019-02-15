package com.noesinformatica.test;

/**
 * Hello world!
 *
 */

public class App {

	public static void main(String[] args) {
		Term term = new Term();
		term.setTermData("Term from app");
		term.setLastUserId((long) 1000);
		
		DataService.saveTerm(term);
		DataService.getAllTerms();
	}
}


