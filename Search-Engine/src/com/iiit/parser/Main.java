package com.iiit.parser;

public class Main {
	
	public static void main(String[] args) {
        SearchUtils.buildAllIndexFiles(); 
		SearchTerm searchObj = new SearchTerm();
		searchObj.search("MinnesotaEden");
	}

}
