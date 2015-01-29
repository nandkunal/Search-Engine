package com.iiit.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParseCorpusData {

	public  void parseCorpusUnFormattedData(String inputFilePath,String documentDirName) {

		String documentFilePath=documentDirName;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		System.out.println("Parsing of Corpus Data started....");
		long start=System.currentTimeMillis();
		try {
			SAXParser saxParser = factory.newSAXParser();
			
				saxParser.parse(inputFilePath, new SaxParserHandler(documentFilePath));
			
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (SAXException e) {
			
			//System.exit(0);
			//e.printStackTrace();
			System.err.print("Parsing Encountered an Error!!");
		}catch(IOException io)
		{
			io.printStackTrace();
		}finally{
			System.out.println("Parsing of Corpus Data is Completed....");
			long end=System.currentTimeMillis();
			long diff = end-start;
			System.out.println("Time Taken to Complete Parsing and Writing Corpus data is "+diff+" milliseconds");
		}
	 
		
		
	}

}
