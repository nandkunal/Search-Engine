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
			String token = tokenizer.nextToken();
			token=token.toLowerCase().trim();//Converting all To Lower Case
			token=token.replaceAll("\\,", "");
			if(!token.contains("_") && !(token.length()==1)){
			if(!SearchUtils.getStopWordsList().contains(token)){
				outputStr.append(token);
				outputStr.append("|");//delimeter
			}
			}
			
		}
		return outputStr.toString().trim();
	}
	
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
	
	public static void buildAllIndexFiles(String inputFilePath,String documentDirName,String invertedIndexFileName)
	{   
		
		ParseCorpusData parser = new ParseCorpusData();
		parser.parseCorpusUnFormattedData(inputFilePath,documentDirName);
		InvertedIndex iv=new InvertedIndex();
        iv.createInvertedIndex(documentDirName, invertedIndexFileName);
	}

}
