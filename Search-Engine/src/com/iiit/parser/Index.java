package com.iiit.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Index 
{
	
	public void buildIndex(String tokenString,Map<String, List<Postings>> invertedIndexMap,String docId)
	{
		           System.out.println(" Started Building Index for document ID"+docId);
		            StringTokenizer tokenizer = new StringTokenizer(tokenString,"|");
		            while(tokenizer.hasMoreTokens())
		            {   
		            	String term = tokenizer.nextToken();
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

		            System.out.println(" Building Index for document ID"+docId + " is Completed!!!");
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
