package com.example.lr4;

public class University {
	private String univ_name;
	private int rating;
	private String city;
	
	public University(String univ_name, int rating, String city) {
		super();
		this.univ_name = univ_name;
		this.rating = rating;
		this.city = city;
	}

	public String getUniv_name() {
		return univ_name;
	}

	public void setUniv_name(String univ_name) {
		this.univ_name = univ_name;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public University(String name, String rating, String city) {
		this.univ_name = name;
		this.rating = Integer.parseInt(rating);
		this.city = city;
	}



	
}
