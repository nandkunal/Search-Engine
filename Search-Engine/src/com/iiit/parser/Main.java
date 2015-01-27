package com.iiit.parser;

public class Main {
	
	public static void main(String[] args) {
		
		String rootPath="/iiit-hyd/IRE/assignment/corpus";
		String inputFileName="test.xml";
		String documentDirName="documents";
		String indexFileName="index";
        SearchUtils.buildAllIndexFiles(rootPath,inputFileName,documentDirName,indexFileName); 
		
	}

}
