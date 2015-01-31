package com.iiit.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParseCorpusData {

	public  void parseCorpusUnFormattedData(String inputFilePath,String documentDirName,String indexFileName) {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		Map<String,List<Postings>> invertedIndexMap = new HashMap<String,List<Postings>>();
		System.out.println("Process Started...");
		long start=System.currentTimeMillis();
		try {
			SAXParser saxParser = factory.newSAXParser();
				saxParser.parse(inputFilePath, new SaxParserHandler(invertedIndexMap));
			
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (SAXException e) {
			System.err.print("Parsing Encountered an Error!!");
		}catch(IOException io)
		{
			io.printStackTrace();
		}finally{
			buildInvertedindex(invertedIndexMap,documentDirName,indexFileName);
			System.out.println("Process Finished....");
			long end=System.currentTimeMillis();
			long diff = end-start;
			System.out.println("Time Taken to Complete Parsing and creating inverted index is "+diff+" milliseconds");
		}
	 
		
		
	}


	private void buildInvertedindex(Map<String,List<Postings>> invertedIndexMap,String documentDirName,String invertedIndexFile) {
	
     System.out.println("Dumping IndexMap From Memory to Index File");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(documentDirName+File.separator+invertedIndexFile);
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
				System.out.println("Dumping IndexMap From Memory to Index File is Finished..."); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			

}
}
