package com.iiit.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

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
	
	public static void createRawDataDocumentDirectory(String documentDirPath)
	{
		
		File plainDataFile = new File(documentDirPath);
		
			if(!plainDataFile.exists())//Don't Create Corpus Directory Each time
			plainDataFile.mkdir();
		
	}
	
	public static String tokenizeString(String inputStr)
	{   
		StringBuilder outputStr=new StringBuilder();
		StringTokenizer tokenizer = new StringTokenizer(inputStr, " ");
		while(tokenizer.hasMoreTokens())
		{
			outputStr.append(tokenizer.nextToken());
			outputStr.append("|");//delimeter
		}
		return outputStr.toString().trim();
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
	
	public static void buildAllIndexFiles(String rootPath,String inputFileName,String documentDirName,String invertedIndexFileName)
	{
		ParseCorpusData parser = new ParseCorpusData();
		parser.parseCorpusUnFormattedData(rootPath,inputFileName,documentDirName);
		InvertedIndex iv=new InvertedIndex();
        iv.createInvertedIndex(rootPath, documentDirName, invertedIndexFileName);
	}

}
