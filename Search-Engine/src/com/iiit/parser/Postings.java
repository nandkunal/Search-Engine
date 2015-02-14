package com.iiit.parser;

import java.util.HashMap;
import java.util.Map;

public class Postings {
	
	private String documentID;
	private int termFrequency;
	private Map<Character,Integer>multiFields=new HashMap<Character,Integer>();
	public Postings()
	{
		multiFields.put('t', 0);
		multiFields.put('b', 0);
		multiFields.put('l', 0);
		multiFields.put('c', 0);
	}
	public String getDocumentID() {
		return documentID;
	}
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	public int getTermFrequency() {
		return termFrequency;
	}
	public void setTermFrequency(int termFrequency) {
		this.termFrequency = termFrequency;
	}
	@Override
	public String toString() {
		return documentID+"::"+termFrequency+"::"+multiFields;
	}
	public Map<Character, Integer> getMultiFields() {
		return multiFields;
	}
	public void setMultiFields(Map<Character, Integer> multiFields) {
		this.multiFields = multiFields;
	}

}
