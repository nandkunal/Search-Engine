package com.iiit.parser;

public class Main {
	
	public static void main(String[] args) {
		
		
		//String inputFilePath="/iiit-hyd/IRE/assignment/corpus/test.xml";
		String inputFilePath=args[0];
		String documentDirName="Index";
		String indexFileName="inverted_index";
        SearchUtils.buildAllIndexFiles(inputFilePath,documentDirName,indexFileName); 
		
	}

}
