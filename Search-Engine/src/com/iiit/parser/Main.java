package com.iiit.parser;

public class Main {
	
	public static void main(String[] args) {
		//Tokenizer tokenizer = new Tokenizer("/iiit-hyd/IRE/resources/formatted-corpus.txt");
		//tokenizer.tokenizeFormattedFile();
		//InvertedIndex iv=new InvertedIndex();
		//iv.buildIndex("/iiit-hyd/IRE/resources/token.txt", "/iiit-hyd/IRE/resources/formatted-corpus.txt");
		//TermOffset termOffset = new TermOffset();
		//termOffset.buildTermOffset("/iiit-hyd/IRE/resources/index.txt");
		SearchTerm searchObj = new SearchTerm();
		searchObj.search("Hunterdon");
	}

}
