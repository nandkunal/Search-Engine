package com.iiit.parser;

public class CreateSecondaryIndex {

	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		String secondaryIndexdir="/iiit-hyd/IRE/assignment/Search-Engine/Search-Engine/Index";
		String indexPath="/iiit-hyd/IRE/assignment/Search-Engine/Search-Engine/Index/inverted_index";
		SecondaryIndex si = new SecondaryIndex(secondaryIndexdir, indexPath);
		si.createSecondaryIndexFile();
		System.out.println("Secondary Index created Successfully ...");
		long end = System.currentTimeMillis();
		float diff= (end-start)/1000;
		System.out.println("Time Taken to create secondary index is "+diff+" seconds");

	}

}
