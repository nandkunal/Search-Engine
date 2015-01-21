package com.iiit.parser;

import java.io.File;
import java.io.IOException;

public class SearchUtils {
	
	public static String  createRawDataFileFromCorpus()
	{
		String path="C:\\IIIT-Hyd-Resources\\IRE\\formatted-corpus.txt";
		File plainDataFile = new File(path);
		try {
			plainDataFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}

}
