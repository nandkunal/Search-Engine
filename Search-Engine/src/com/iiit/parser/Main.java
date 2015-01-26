package com.iiit.parser;

public class Main {
	
	public static void main(String[] args) {
		
		String rootPath="/iiit-hyd/IRE/assignment/corpus";
		String inputFileName="sample.xml";
		String documentDirName="documents";
       SearchUtils.buildAllIndexFiles(rootPath,inputFileName,documentDirName); 
		//SearchTerm searchObj = new SearchTerm();
		//searchObj.search("MinnesotaEden");
	}

}
