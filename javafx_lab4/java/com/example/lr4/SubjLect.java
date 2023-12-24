package com.example.lr4;

public class SubjLect {
	private String lector;
	private String subj;
	
	public SubjLect(String lector, String subj) {
		this.lector = lector;
		this.subj = subj;
	}

	public String getLector() {
		return lector;
	}

	public void setLector(String lector) {
		this.lector = lector;
	}

	public String getSubj() {
		return subj;
	}

	public void setSubj(String subj) {
		this.subj = subj;
	}

	@Override
	public String toString() {
		return "SubjLect [lector=" + lector + ", subj=" + subj + "]";
	}
	
	

}
