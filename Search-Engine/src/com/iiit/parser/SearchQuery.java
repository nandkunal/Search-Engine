package com.iiit.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class SearchQuery {
	
	private String indexFilePath;
	
	private Map<String,String> indexMap;
	
	public SearchQuery(String indexFilePath)
	{
		this.indexFilePath=indexFilePath;
		loadIndexInMemory();
		
	}
	
	
	public void search(String query){
		
		query=query.toLowerCase().trim();
		if(query.contains(":"))
		{
			//MultiField Query 
			Map<String,String> queryFieldMap=new TreeMap<String,String>();
			String[] fields = query.split(" ");
			for(String field:fields)
			{
				String[] queryTerm=field.split(":");
				if(queryTerm.length==2){
					queryFieldMap.put(queryTerm[0], queryTerm[1]);
				}
			}
			searchMutipleFieldQuery(queryFieldMap);
		}else{
		if(query.contains(" ")){
			query=removeWhiteSpace(query);
		}
		if(indexMap.containsKey(query))
		{
			System.out.println(indexMap.get(query));
		}else{
			System.out.println("No Result Found");
		}
		}
	}
	private void searchMutipleFieldQuery(Map<String, String> queryFieldMap) {
		if(queryFieldMap.size()>0){
			Set<Entry<String,String>> entrySet=queryFieldMap.entrySet();
			Iterator<Entry<String, String>> itr=entrySet.iterator();
			while(itr.hasNext())
			{
				Entry<String,String> entry= itr.next();
				String key = entry.getKey();
				String val=entry.getValue();
				if(indexMap.containsKey(val))
				{
					String postlistStr= indexMap.get(val);
					String[] postingTokenspost=postlistStr.split(",");
					for(String posts:postingTokenspost)
					{
						String[] termArr=posts.split(":");
						for(int j=1;j<termArr.length;j++)//Ignoring DocumentID-TF entry
						{
							String[] mulFields=termArr[j].split("-");
							if(mulFields[0].equalsIgnoreCase(key)){
								if(!mulFields[1].equalsIgnoreCase("0")){
									System.out.println(posts);
								}
							}
						}
						
					}
					
				}
			}
		}
		
	}


	private String removeWhiteSpace(String query) {
		StringBuilder builder=new StringBuilder();
		String[] tokens = query.split("\\s");
		 for(String s : tokens)
		 {
			 builder.append(s);
		 }
		return builder.toString();
	}


	private void loadIndexInMemory()
	{
		Scanner fileReader=null;
		indexMap= new HashMap<String,String>();
		
		try {
			fileReader= new Scanner(new File(indexFilePath));
			fileReader.useDelimiter("\n");
			while(fileReader.hasNext())
			{
				String line = fileReader.next();
				String[] tokens=line.split("\\|");
				indexMap.put(tokens[0], tokens[1]);
				
			}
		} catch(NoSuchElementException ne){
			System.err.println("Error");
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally
		
		{
			fileReader.close();
		}
		
	}

}
