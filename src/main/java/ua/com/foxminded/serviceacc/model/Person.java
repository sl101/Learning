package ua.com.foxminded.serviceacc.model;

import java.util.List;

public class Person {
	private Long id;
	private String firstName;
	private String lastName;
	private List<Detail> details;
	
	public List<Detail> addDetail(Detail detail) {
		details.add(detail);
		return details;
	}	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Detail> getDetails() {
		return details;
	}
	public void setDetails(List<Detail> details) {
		this.details = details;
	}
	
	
}
