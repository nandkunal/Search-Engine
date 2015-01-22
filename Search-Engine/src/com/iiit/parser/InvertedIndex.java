package com.iiit.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class InvertedIndex {
	
	private Map<String,List<Postings>> invertedIndexMap;
	
	
	public void buildIndex(String tokenFile,String formattedCorpusFile){
		
		Map<String,Integer>TermTFMap=new HashMap<String,Integer>();
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
		            StringTokenizer tokenizer = new StringTokenizer(text,"###");
		            while(tokenizer.hasMoreTokens())
		            {   
		            	String term = tokenizer.nextToken();
		            	if(TermTFMap.containsKey(term)){
		            		TermTFMap.put(term, TermTFMap.get(term)+1);
		            	}else{
		            		
		            		TermTFMap.put(term, 0);
		            	}
		            }

		            if(read < N) {
		                break;
		            }
		        }
		     
		     System.out.println(TermTFMap);
		     buildInvertedindex(TermTFMap);
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


	private void buildInvertedindex(Map<String, Integer> termTFMap) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {

			String invertedIndexFile="/iiit-hyd/IRE/resources/index.txt";
			fw = new FileWriter(invertedIndexFile,true);
			bw = new BufferedWriter(fw);


			for (Entry<String, Integer> entry : termTFMap.entrySet())
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
	
	
	

}
