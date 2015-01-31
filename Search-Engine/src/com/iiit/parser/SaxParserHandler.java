package com.iiit.parser;

import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParserHandler extends DefaultHandler {
	boolean text = false;
	boolean id = false;
	Map<String,List<Postings>> invertedIndexMap = null;
	String documentDirPath;
	int docId=1;
	String documentID;
	
	public SaxParserHandler(Map<String,List<Postings>> invertedIndexMap)
	{
		
		this.invertedIndexMap=invertedIndexMap;
	}
	
	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {
		
	
		if(qName.equalsIgnoreCase("text"))
		{
			text=true;
			docId++;
		}if(qName.equalsIgnoreCase("id"))
		{
			id=true;
		}
		
	}
	
	 public void characters (char ch[], int start, int length)
		        throws SAXException
	 {  
		 if(id){
			 documentID = new String(ch, start, length);
		 }
		 
		 if (text) {
		
             String tokenizedString =null;
			 String value = new String(ch, start, length);
				 if(value.length()!=0)//Check if tag is not empty
				 {   
					 //Perform all Kinds of 
					 //1.Invalid character removal
					 //2.Filtering words
					 //3.Removing empty space and lines
					 //4.Removing extra characters
					 value=value.replaceAll("\\s{2,}","");//Removing More Than One Empty Spaces
					 value=value.replaceAll("\\d", "");//Remove all Numeric characters
					 value=value.replaceAll("\\.", "");
					 value=value.replaceAll("\\[|\\]|\\{|\\}|\\(|\\|\\)|\\\n|\\<|\\>|\\-+|\\=|\\|","");//Removing extra characters
					 value=value.replaceAll("&nbsp;", " ");
					 //Now Tokenizing in Memory
					 tokenizedString=SearchUtils.tokenizeString(value);
					 Index indexObj = new Index();
					 indexObj.buildIndex(tokenizedString, invertedIndexMap, documentID.trim());
					 
					
					 
				 }
			 
			 
		
		 
		 }
	 }
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
	  
		if(qName.equalsIgnoreCase("text"))
		{
			text=false;
		}if(qName.equalsIgnoreCase("id"))
		{
			id=false;
		}
	 
		}
	
}
