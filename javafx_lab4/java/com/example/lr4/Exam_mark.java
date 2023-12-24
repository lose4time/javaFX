package com.example.lr4;

public class Exam_mark {
	private String student;
	private String subj;
	private int mark;
	private String date;
	
	public Exam_mark(String student, String subj, int mark, String date) {
		this.student = student;
		this.subj = subj;
		this.mark = mark;
		this.date = date;
	}
	
	public Exam_mark(String student, String subj, String mark, String date) {
		this.student = student;
		this.subj = subj;
		this.mark = Integer.parseInt(mark);
		this.date = date;
	}
	
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getSubj() {
		return subj;
	}
	public void setSubj(String subj) {
		this.subj = subj;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Exam_mark [student=" + student + ", subj=" + subj + ", mark=" + mark + ", date=" + date + "]";
	}
	
	
}
