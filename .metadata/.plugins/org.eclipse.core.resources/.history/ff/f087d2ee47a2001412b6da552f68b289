package com.iiit.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchUtils {
	
	public static File  createRawDataFileFromCorpus(String path,String fileName)
	{
		
		File plainDataFile = new File(path+File.separator+fileName);
		try {
			if(!plainDataFile.exists())//Don't Create Corpus File Each time
			plainDataFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return plainDataFile;
	}
	
	public static List<String> getStopWordsList(){
		List<String> stopWordList = new ArrayList<String>();
		try {
			Scanner reader= new Scanner(new File("stopwords.txt"));
			reader.useDelimiter("\n");
			while(reader.hasNext()){
				stopWordList.add(reader.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stopWordList;
	}
	
	public static void buildAllIndexFiles()
	{
		String datafile="C:\\IIIT-Hyd-Assignments\\IRE\\sample.xml";
		ParseCorpusData parser = new ParseCorpusData();
		parser.parseCorpusUnFormattedData(datafile);
		Tokenizer tokenizer = new Tokenizer("C:\\IIIT-Hyd-Assignments\\IRE\\formatted-corpus.txt");
		tokenizer.tokenizeFormattedFile();
		InvertedIndex iv=new InvertedIndex();
		iv.buildIndex("C:\\IIIT-Hyd-Assignments\\IRE\\token.txt", "C:\\IIIT-Hyd-Assignments\\IRE\\formatted-corpus.txt");
		//TermOffset termOffset = new TermOffset();
		//termOffset.buildTermOffset("C:\\IIIT-Hyd-Assignments\\IRE\\index.txt");
	}

}
