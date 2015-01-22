package com.iiit.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class InvertedIndex {
	
	
	
	
	public void buildIndex(String tokenFile,String formattedCorpusFile){
		System.out.println("Building Inverted Index File Started...");
		Map<String,Integer>TermTFMap=new HashMap<String,Integer>();
		Map<String,List<Postings>> invertedIndexMap = new HashMap<String,List<Postings>>();
		 int read, N = 1024 * 1024;
		  char[] buffer = new char[N];
		File tokenDataFile=new File(tokenFile);
		 FileReader fr = null;
	        BufferedReader br = null;
		try {
			 fr = new FileReader(tokenDataFile);
			 br = new BufferedReader(fr);
		     while(true) {
		            read = br.read(buffer, 0, N);
		            String  text = new String(buffer, 0, read);
		            StringTokenizer tokenizer = new StringTokenizer(text,"##");
		            while(tokenizer.hasMoreTokens())
		            {   
		            	String term = tokenizer.nextToken();
		            	if(invertedIndexMap.containsKey(term)){
		            		int docId=1;
		            		List<Postings>postlist=invertedIndexMap.get(term);
		            		Postings post = getPostingByDocId(docId, postlist);
		            		if(post!=null){
		            			post.setTermFrequency(post.getTermFrequency()+1);
		            			postlist.add(post);
		            			invertedIndexMap.put(term, postlist);
		            		}
		            		
		            	}else{
		            		Postings posting= new Postings();
		            		posting.setDocumentID(1);
		            		posting.setTermFrequency(1);
		            		List<Postings> postlist=new ArrayList<Postings>();
		            		postlist.add(posting);
		            		invertedIndexMap.put(term,postlist);
		            		TermTFMap.put(term, 1);
		            	}
		            }

		            if(read < N) {
		                break;
		            }
		        }
		     
		     //System.out.println(TermTFMap);
		     buildInvertedindex(invertedIndexMap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
	    	try {
				br.close();
				fr.close();
				System.out.println("Building Token File Completed!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
	}


	private void buildInvertedindex(Map<String,List<Postings>> invertedIndexMap) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {

			String invertedIndexFile="C:\\IIIT-Hyd-Assignments\\IRE\\index.txt";
			fw = new FileWriter(invertedIndexFile,true);
			bw = new BufferedWriter(fw);


			for (Entry<String, List<Postings>> entry : invertedIndexMap.entrySet())
			{
				bw.write(entry.getKey()+"##"+entry.getValue());
				bw.newLine();
			}




		}catch(Exception ex){
          ex.printStackTrace();
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
	
	private Postings getPostingByDocId(int docId,List<Postings> postlist)
	{
		for(Postings post:postlist)
		{
			if(post.getDocumentID()==docId)
				return post;
		}
		return null;
	}
	

}
