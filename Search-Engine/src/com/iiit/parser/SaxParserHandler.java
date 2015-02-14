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
	String pageTitle=null;
	List<String> stopWordlist=null;
	Pattern pattern = null;
	Matcher matcher=null;
	boolean title=false;
	String lastElement=null;
	
	public SaxParserHandler(Map<String,List<Postings>> invertedIndexMap,List<String> stopWordlist)
	{
		
		this.invertedIndexMap=invertedIndexMap;
		this.stopWordlist=stopWordlist;
		pattern = Pattern.compile("(^[a-z]+$)");
		
	}
	
	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {
		
		if(qName.equalsIgnoreCase("title"))
		{
			title=true;
		}
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
		
		 if(title){
			 String titleText = new String(ch, start, length);
			 if(titleText.length()!=0)//Check if tag is not empty
			 {   
				String[] full_name_arr=titleText.split(",");
				StringBuilder titleVal= new StringBuilder();
				if(full_name_arr!=null){
					if(full_name_arr.length==1){
						titleVal.append(full_name_arr[0].trim());
					}
					if(full_name_arr.length==2){
						titleVal.append(full_name_arr[0].trim());
						titleVal.append(full_name_arr[1].trim());
					}
				}
				
				pageTitle=titleVal.toString();
				 
				
				 
			 }
		 }
		 if(id && lastElement.equalsIgnoreCase("title")){//Need to check which Id as there are lot of ids in the different levels
			 documentID = new String(ch, start, length);
			 tokenizeStringAndPopulateIndexMap(pageTitle,documentID,'t');
		 }
		 if (text) {
		
			 String value = new String(ch, start, length);
				 if(value.length()!=0)//Check if tag is not empty
				 {   
					 
					 tokenizeStringAndPopulateIndexMap(value,documentID,'b');
					 
					
					 
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
		if(qName.equalsIgnoreCase("title"))
		{
			title=false;
		}
		lastElement=qName;
	}
	
	private void tokenizeStringAndPopulateIndexMap(String str,String docId,char fieldType)
	{
		for(String word:str.split("\\s"))
		{
			word=word.trim().toLowerCase();
			Matcher matcher = pattern.matcher(word);
			if (matcher.find() && !stopWordlist.contains(word) && word.length()>=3)
			  {
				buildInvertedIndex(word,docId,fieldType);
			  }
		}
	}
	
	private void buildInvertedIndex(String term,String docId,char fieldType)
	{
		if(invertedIndexMap.containsKey(term)){
    		List<Postings>postlist=invertedIndexMap.get(term);
    		Postings post = getPostingByDocId(docId, postlist);
    		
    		if(post!=null){
    			post.setTermFrequency(post.getTermFrequency()+1);
    			 int val =post.getMultiFields().get(fieldType);
    			post.getMultiFields().put(fieldType, val+1);
    			
    			invertedIndexMap.put(term, postlist);
    		}else{
    			Postings newPost= new Postings();
    			newPost.setDocumentID(docId);
    			newPost.setTermFrequency(1);
    			int val =newPost.getMultiFields().get(fieldType);
    			newPost.getMultiFields().put(fieldType, val+1);
    			postlist.add(newPost);
    		}
    		
    	}else{
    		Postings posting= new Postings();
    		posting.setDocumentID(docId);
    		posting.setTermFrequency(1);
    		int val =posting.getMultiFields().get(fieldType);
    		posting.getMultiFields().put(fieldType, val+1);
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
