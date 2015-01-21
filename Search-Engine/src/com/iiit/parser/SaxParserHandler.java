package com.iiit.parser;

import java.io.File;

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
		System.out.println("Will be Called Once ----");
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
		 String value = new String(ch, start, length);
		 if (pageTitle) {
			 if(value.length()!=0)//Removing Empty Spaces
			 {
				 System.out.println("Page Title : " +value);
				 pageTitle = false;
			 }
		 }
		 if (username) {
			 if(value.length()!=0)
			 {
				 System.out.println("Username : " +value);
				 username = false;
			 }
			}
		 if (comment) {
			 if(value.length()!=0)
			 {
				 System.out.println("comment : " +value);
				 comment = false;
			 }
			}
		 if (text) {
			 if(value.length()!=0)
			 {
				 System.out.println("Text : " +value);
				 text = false;
			 }
			}
		    }
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
	 
			//System.out.println("End Element :" + qName);
	 
		}
	
}
