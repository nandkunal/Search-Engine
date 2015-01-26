package com.iiit.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParserHandler extends DefaultHandler {
	boolean page = false;
	int documentID=0;
	String documentDirPath;
	
	public SaxParserHandler(String documentDirPath)
	{
		
		this.documentDirPath=documentDirPath;
		SearchUtils.createRawDataDocumentDirectory(documentDirPath);
		
		
		
	}
	
	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {
		
	
		if(qName.equalsIgnoreCase("page"))
		{
			page=true;
			documentID++;
		}
		
	}
	
	 public void characters (char ch[], int start, int length)
		        throws SAXException
	 {  
		 if (page) {
		 String fileName="datafile_"+documentID;
		 FileWriter fw = null;
		 BufferedWriter bw =null;
		 File documentFile=new File(documentDirPath+File.separator+fileName);
		 try {
			 fw = new FileWriter(documentFile,true);
			 bw = new BufferedWriter(fw);

			 String value = new String(ch, start, length);
			
				 if(value.length()!=0)//Check if tag is not empty
				 {   
					 //value.replaceAll("\\s+","");//Removing Empty Spaces and Lines
					 bw.write(value);
					 page = false;
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
	 }
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
	 
			
	 
		}
	
}
