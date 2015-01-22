package com.iiit.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Tokenizer {
	
	String filePath;
	
	
	public Tokenizer(String filePath){
		this.filePath=filePath;
		
	}
	
	public void tokenizeFormattedFile(){

	    int read, N = 1024 * 1024;
	    char[] buffer = new char[N];
	    File termToken= new File("/iiit-hyd/IRE/resources/token.txt");
	    FileReader fr = null;
        BufferedReader br = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
	    try {
	        fr = new FileReader(filePath);
	        br = new BufferedReader(fr);
	        fw = new FileWriter(termToken,true);
	        bw = new BufferedWriter(fw);

	        while(true) {
	            read = br.read(buffer, 0, N);
	            String  text = new String(buffer, 0, read);
	            StringTokenizer tokenizer = new StringTokenizer(text," ");
	            while(tokenizer.hasMoreTokens())
	            {
	            	bw.write(tokenizer.nextToken());
	            	bw.write("###");
	            }

	            if(read < N) {
	                break;
	            }
	        }
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }finally{
	    	try {
				bw.close();
				fw.close();
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	   
	}

}
