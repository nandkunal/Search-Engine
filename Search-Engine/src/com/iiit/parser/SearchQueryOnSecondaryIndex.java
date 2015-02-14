package com.iiit.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class SearchQueryOnSecondaryIndex {

	private String indexFilePath;

	private List<SecondaryIndexEntity> indexEntity;
	
	private String invertedIndexFilePath;

	public SearchQueryOnSecondaryIndex(String indexFilePath,String invertedIndexFilePath)
	{
		this.indexFilePath=indexFilePath;
		this.invertedIndexFilePath=invertedIndexFilePath;
		loadIndexInMemory();

	}

	private void loadIndexInMemory() {
		indexEntity = new ArrayList<SecondaryIndexEntity>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(indexFilePath));
			scanner.useDelimiter("\n");
			while(scanner.hasNext())
			{
				String[] pairs = scanner.next().split("-");

				SecondaryIndexEntity token = new SecondaryIndexEntity();
				token.setTerm(pairs[0]);
				token.setOffset(Long.parseLong(pairs[1]));
				indexEntity.add(token);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally
		{
			scanner.close();
		}


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
		
		long offset = binarySearch(query, 0, indexEntity.size()-1);
		if(offset!=-1)
		{
			System.out.println(seekInvertedIndexFile(offset,invertedIndexFilePath));
		}else{
			System.err.println("Record Not Found");
		}
		}
	}

	private void searchMutipleFieldQuery(Map<String, String> queryFieldMap) {
		if(queryFieldMap.size()>0)
		{
			Set<Entry<String,String>> entrySet=queryFieldMap.entrySet();
			Iterator<Entry<String, String>> itr=entrySet.iterator();
			while(itr.hasNext())
			{
				Entry<String,String> entry= itr.next();
				String key = entry.getKey();
				String val=entry.getValue();
				long offset = binarySearch(val, 0, indexEntity.size()-1);
				if(offset!=-1)
				{
					String post=seekInvertedIndexFile(offset,invertedIndexFilePath);
					String[] mulKey = post.split(":");
					for(int j=1;j<mulKey.length;j++)
					{
						String[] pairs = mulKey[j].split("-");
						if(pairs[0].equalsIgnoreCase(key) && !pairs[1].equalsIgnoreCase("0"))
						{
							System.out.println(post);
						}
						
					}
				}else{
					System.out.println("Record Not Found");
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
      
	
	private long binarySearch(String term,int s,int l)
	{   
		int m=(s+l)/2;
		if(term.equalsIgnoreCase(indexEntity.get(m).getTerm()))
		{  
			
			return indexEntity.get(m).getOffset();
			
		}else{
			if(term.compareToIgnoreCase(indexEntity.get(m).getTerm())<0)
			{
				l=m-1;
				return binarySearch(term, s, l);
			}else if(term.compareToIgnoreCase(indexEntity.get(m).getTerm())>0)
			{
				s=m+1;
				return binarySearch(term, s, l);
			}
			
		}
		
		if(s>m)
		{
			return -1;
		}
		
		return -1;
		
	}

	private String seekInvertedIndexFile(long offset, String invertedIndexFilePath)
	{
		String line = null;
		RandomAccessFile randomFile = null;
		try {
			randomFile = new  RandomAccessFile(invertedIndexFilePath, "r");
			try {
				randomFile.seek(offset);
				 line = randomFile.readLine();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				randomFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return line.split("\\|")[1];
		
	}


}
