package com.iiit.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SearchUtils {
	

	

	public static List<String> getStopWordsList(){
		List<String> stopWordList = new ArrayList<String>();
		Scanner reader=null;
		try {
			reader= new Scanner(new File("stopwords.txt"));
			reader.useDelimiter("\n");
			while(reader.hasNext()){
				stopWordList.add(reader.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			reader.close();
		}
		
		return stopWordList;
	}
	
	public static void buildAllIndexFiles(String inputFilePath,String documentDirName,String invertedIndexFileName, List<String> stopWordlist)
	{   
		
		ParseCorpusData parser = new ParseCorpusData();
		parser.parseCorpusUnFormattedData(inputFilePath,documentDirName,invertedIndexFileName,stopWordlist);
		
	}

}
