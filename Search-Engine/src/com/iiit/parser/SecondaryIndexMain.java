package com.iiit.parser;

import java.io.File;

public class SecondaryIndexMain {

	public static void main(String[] args) {
		
		
		
		String secondaryIndexdir="/iiit-hyd/IRE/assignment/Search-Engine/Search-Engine/Index";
		
		String indexPath=args[0];
		
		SearchQueryOnSecondaryIndex se = new SearchQueryOnSecondaryIndex(secondaryIndexdir+File.separator+"secondary_index",indexPath);
		for(int i=1;i<args.length;i++){
        long start=System.currentTimeMillis();
		se.search(args[i]);
		long end = System.currentTimeMillis();
		float diff= (end-start)/1000;
		System.out.println("Time Taken to search query is "+diff+" seconds");
		}
		

	}

}
