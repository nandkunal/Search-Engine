package com.iiit.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SecondaryIndex {
	
	
	private String path;
	private String indexFilePath;
	public SecondaryIndex(String path,String indexFilePath)
	{
		this.path=path; 
		this.indexFilePath=indexFilePath;
	}
	
	
	public void createSecondaryIndexFile()
	{
		File f = new File(path+File.separator+"secondary_index");
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dumpData();
	}
	
	private void dumpData() {
		int offset=0;
		FileReader fileReader=null;
		FileWriter writer= null;
		BufferedReader br=null;
		BufferedWriter bw = null;
		try {
			fileReader = new FileReader(indexFilePath);
			writer = new FileWriter(path+File.separator+"secondary_index");
			br = new BufferedReader(fileReader);
			bw = new BufferedWriter(writer);
			
			String line;
			
				line = br.readLine();
			
			while(line!=null)
			{
			   String[] term = line.split("\\|");
			   bw.write(term[0]+"-"+offset);
			   bw.write("\n");
				offset=offset+line.length()+1;
				line=br.readLine();
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				
				bw.close();
				br.close();
				writer.close();
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
