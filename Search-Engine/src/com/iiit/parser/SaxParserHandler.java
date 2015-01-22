package com.iiit.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParserHandler extends DefaultHandler {
	boolean pageTitle=false;
	boolean comment=false;
	boolean username=false;
	boolean text=false;
	File formattedFile=null;
	
	
	public SaxParserHandler()
	{
		String path="C:\\IIIT-Hyd-Assignments\\IRE";
		String fileName="formatted-corpus.txt";
		formattedFile=SearchUtils.createRawDataFileFromCorpus(path, fileName);
		
		
	}
	
	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {
		//System.out.println("Start Element :" + qName);
	
		
		if(qName.equalsIgnoreCase("title"))
		{
			pageTitle=true;
		}
		if(qName.equalsIgnoreCase("username"))
		{
			username=true;
		}
		if(qName.equalsIgnoreCase("comment"))
		{
			comment=true;
		}
		if(qName.equalsIgnoreCase("text"))
		{
			text=true;
		}
	}
	
	 public void characters (char ch[], int start, int length)
		        throws SAXException
	 {
		 FileWriter fw = null;
		 BufferedWriter bw =null;
		 try {
			 fw = new FileWriter(formattedFile,true);
			 bw = new BufferedWriter(fw);

			 String value = new String(ch, start, length);
			 if (pageTitle) {
				 if(value.length()!=0)//Removing Empty Spaces
				 {
					 bw.write(value);
					 pageTitle = false;
				 }
			 }
			 if (username) {
				 if(value.length()!=0)
				 {
					 bw.write(value);
					 username = false;
				 }
			 }
			 if (comment) {
				 if(value.length()!=0)
				 {
					 bw.write(value);
					 comment = false;
				 }
			 }
			 if (text) {
				 if(value.length()!=0)
				 {
					 
					 bw.write(value);
					 text = false;
				 }
			 }
		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }finally{
			 try {
				 bw.close();
				 fw.close();
			 } catch (IOException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
		 }
	 }
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
	 
			//System.out.println("End Element :" + qName);
	 
		}
	
}
