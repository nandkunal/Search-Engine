package com.iiit.parser;

import java.io.File;
import java.io.IOException;

public class SearchUtils {
	
	public static File  createRawDataFileFromCorpus(String path,String fileName)
	{
		
		File plainDataFile = new File(path+File.separator+fileName);
		try {
			plainDataFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return plainDataFile;
	}
	

}
