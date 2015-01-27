package com.iiit.parser;

public class Postings {
	
	private String documentID;
	private int termFrequency;
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
		return documentID+"::"+termFrequency;
	}

}
