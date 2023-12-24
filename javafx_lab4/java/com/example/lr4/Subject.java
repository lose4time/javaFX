package com.example.lr4;

public class Subject {
	private String subj_name;
	private int hours;
	private int semester;
	
	public Subject(String subj_name, String hours, String semester) {
		this.subj_name = subj_name;
		this.hours = Integer.parseInt(hours);
		this.semester = Integer.parseInt(semester);
	}

	public Subject(String subj_name, int hours, int semester) {
		super();
		this.subj_name = subj_name;
		this.hours = hours;
		this.semester = semester;
	}

	public String getSubj_name() {
		return subj_name;
	}

	public void setSubj_name(String subj_name) {
		this.subj_name = subj_name;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	@Override
	public String toString() {
		return "Subject [subj_name=" + subj_name + ", hours=" + hours + ", semester=" + semester + "]";
	}
}
