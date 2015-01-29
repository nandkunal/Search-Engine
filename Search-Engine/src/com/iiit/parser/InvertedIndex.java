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
	
	
	
	public void createInvertedIndex(String rootPath,String documentDirName,String invertedIndexFileName)
	{
		System.out.println("Building Inverted Index File Started...");
		long start=System.currentTimeMillis();
		Map<String,List<Postings>> invertedIndexMap = new HashMap<String,List<Postings>>();
		File f =new File(rootPath+File.separator+documentDirName);
		File index = new File(rootPath+File.separator+invertedIndexFileName);
		File[] docFiles=f.listFiles();
		if(docFiles.length>0)
		{
			for(File document:docFiles)
			{
				buildIndex(document,invertedIndexMap);
			}
			 buildInvertedindex(invertedIndexMap,index);
		}
		System.out.println("Building Inverted File Completed!!!");
		long end=System.currentTimeMillis();
		long diff = end-start;
		System.out.println("Time Taken to Create Index File is "+diff+" milliseconds");
	}
	
	
	
	






	private void buildIndex(File tokenDataFile,Map<String, List<Postings>> invertedIndexMap)
	{
		 int read, N = 1024 * 1024;
		  char[] buffer = new char[N];
		 FileReader fr = null;
	        BufferedReader br = null;
		try {
			 fr = new FileReader(tokenDataFile);
			 br = new BufferedReader(fr);
		     while(true) {
		            read = br.read(buffer, 0, N);
		            String  text = new String(buffer, 0, read);
		            StringTokenizer tokenizer = new StringTokenizer(text,"|");
		            while(tokenizer.hasMoreTokens())
		            {   
		            	String term = tokenizer.nextToken();
		            	if(invertedIndexMap.containsKey(term)){
		            		List<Postings>postlist=invertedIndexMap.get(term);
		            		Postings post = getPostingByDocId(tokenDataFile.getName(), postlist);
		            		
		            		if(post!=null){
		            			post.setTermFrequency(post.getTermFrequency()+1);
		            			invertedIndexMap.put(term, postlist);
		            		}else{
		            			Postings newPost= new Postings();
		            			newPost.setDocumentID(tokenDataFile.getName());
		            			newPost.setTermFrequency(1);
		            			postlist.add(newPost);
		            		}
		            		
		            	}else{
		            		Postings posting= new Postings();
		            		posting.setDocumentID(tokenDataFile.getName());
		            		posting.setTermFrequency(1);
		            		List<Postings> postlist=new ArrayList<Postings>();
		            		postlist.add(posting);
		            		invertedIndexMap.put(term,postlist);
		            		//termTFMap.put(term, 1);
		            	}
		            }

		            if(read < N) {
		                break;
		            }
		        }
		     
		    
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
	}


	private void buildInvertedindex(Map<String,List<Postings>> invertedIndexMap,File invertedIndexFile) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {

			fw = new FileWriter(invertedIndexFile,true);
			bw = new BufferedWriter(fw);


			for (Entry<String, List<Postings>> entry : invertedIndexMap.entrySet())
			{   
				List<Postings> lst = entry.getValue();
				StringBuilder postinglist=new StringBuilder();
				for(Postings p :lst)
				{
					postinglist.append(p.getDocumentID()+":"+p.getTermFrequency());
					postinglist.append(",");
				}
				
				bw.write(entry.getKey()+"|"+postinglist.substring(0,postinglist.length()-1));
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
