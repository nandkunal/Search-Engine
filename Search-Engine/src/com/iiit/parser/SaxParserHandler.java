package com.iiit.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParserHandler extends DefaultHandler {
	boolean text = false;
	boolean id = false;
	private Map<String,List<Postings>> invertedIndexMap = null;
	String documentDirPath;
	String documentID;
	List<String> stopWordlist=null;
	Pattern pattern = null;
	Matcher matcher=null;
	
	
	public SaxParserHandler(Map<String,List<Postings>> invertedIndexMap,List<String> stopWordlist)
	{
		
		this.invertedIndexMap=invertedIndexMap;
		this.stopWordlist=stopWordlist;
		pattern = Pattern.compile("(^[a-z]+$)");
		
	}
	
	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {
		
	
		if(qName.equalsIgnoreCase("text"))
		{
			text=true;
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
		
			 String value = new String(ch, start, length);
				 if(value.length()!=0)//Check if tag is not empty
				 {   
					 
					 tokenizeStringAndPopulateIndexMap(value,documentID);
					 
					
					 
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
	
	private void tokenizeStringAndPopulateIndexMap(String str,String docId)
	{
		for(String word:str.split("\\s"))
		{
			word=word.trim().toLowerCase();
			Matcher matcher = pattern.matcher(word);
			if (matcher.find() && !stopWordlist.contains(word) && word.length()>=3)
			  {
				buildInvertedIndex(word,docId);
			  }
		}
	}
	
	private void buildInvertedIndex(String term,String docId)
	{
		if(invertedIndexMap.containsKey(term)){
    		List<Postings>postlist=invertedIndexMap.get(term);
    		Postings post = getPostingByDocId(docId, postlist);
    		
    		if(post!=null){
    			post.setTermFrequency(post.getTermFrequency()+1);
    			invertedIndexMap.put(term, postlist);
    		}else{
    			Postings newPost= new Postings();
    			newPost.setDocumentID(docId);
    			newPost.setTermFrequency(1);
    			postlist.add(newPost);
    		}
    		
    	}else{
    		Postings posting= new Postings();
    		posting.setDocumentID(docId);
    		posting.setTermFrequency(1);
    		List<Postings> postlist=new ArrayList<Postings>();
    		postlist.add(posting);
    		invertedIndexMap.put(term,postlist);
    	}	
	}
	
	private Postings getPostingByDocId(String docId,List<Postings> postlist)
	{
		for(Postings post:postlist)
		{
			if(post.getDocumentID().equalsIgnoreCase(docId))
				return post;
		}
		return null;
	}


	
	
}
