package com.iiit.parser;

import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
		
		//String inputFilePath="/iiit-hyd/IRE/assignment/corpus/test.xml";
		String inputFilePath=args[0];
		String documentDirName="Index";
		String indexFileName="inverted_index";
        List<String> stopWordlist= SearchUtils.getStopWordsList(); 
        SearchUtils.buildAllIndexFiles(inputFilePath,documentDirName,indexFileName,stopWordlist);
        
		
	}

}
