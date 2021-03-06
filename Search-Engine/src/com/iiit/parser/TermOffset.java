package com.iiit.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class TermOffset {
	
	Map<Integer,String>termOffsetMap;
	
	
	public void buildTermOffset(String invertedIndexFile)
	{   Map<String,Integer>termOffsetMap=new HashMap<String, Integer>();
		Scanner fileReader=null;
		int offset=0;
		try {
			fileReader = new Scanner(new File(invertedIndexFile));
			fileReader.useDelimiter("\n");
			int lineIndex=1;
			while(fileReader.hasNext())
			{
				String line = fileReader.next();
				termOffsetMap.put(line.split("##")[0], offset);
				offset=offset+line.length()+lineIndex;
				lineIndex++;
			}
			//System.out.println(termOffsetMap);
			populateTermOffserFile(termOffsetMap);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			fileReader.close();
		}
	}

	private void populateTermOffserFile(Map<String, Integer> termOffsetMap) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {

			String termOffsetFile="C:\\IIIT-Hyd-Assignments\\IRE\\termoffset.txt";
			fw = new FileWriter(termOffsetFile,true);
			bw = new BufferedWriter(fw);


			for (Entry<String, Integer> entry : termOffsetMap.entrySet())
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
