package com.iiit.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SearchTerm {
	
	
	public void search(String Query)
	{
		int offset= getTermOffset(Query);
		if(offset==-1){
			System.out.println("Term Not Present");
		}else{
			String result = lookupTermOffsetFile(offset);
			System.out.println(result);
			//System.out.println("Term : "+result.split("##")[0]+" occurs "+ result.split("##")[1]+" times in the Document");
		}
	}
	
	private int getTermOffset(String term)
	{
		int offset=-1;
		 Scanner reader=null;
		try {
			reader=new Scanner(new File("/iiit-hyd/IRE/resources/termoffset.txt"));
			
			   while(reader.hasNext()) {
		            
		            String  text = reader.next();
		            if(text.split("##")[0].equalsIgnoreCase(term)){
		            	offset=Integer.parseInt(text.split("##")[1]);
		            	break;
		            }
		            
		        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex) {
	        ex.printStackTrace();
	    }finally{
	    	reader.close();
	    }
		return offset;
				
	}
	
	private String lookupTermOffsetFile(int offset){
		String data="";
		try {
			RandomAccessFile fileAccess = new  RandomAccessFile("/iiit-hyd/IRE/resources/index.txt", "r");
			try {
				fileAccess.seek(offset);
				data = fileAccess.readLine();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

}
