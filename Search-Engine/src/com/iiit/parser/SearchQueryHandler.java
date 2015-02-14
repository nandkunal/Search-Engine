package com.iiit.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchQueryHandler {

	public static void main(String[] args) {
		
		//String indexPath="/iiit-hyd/IRE/assignment/Search-Engine/Search-Engine/Index/inverted_index";
		String indexPath=args[0];
		SearchQuery search= new SearchQuery(indexPath);
		for(int i=1;i<args.length;i++)
		{
			//System.out.println(args[i]);
			search.search(args[i]);
		}

	}

}
