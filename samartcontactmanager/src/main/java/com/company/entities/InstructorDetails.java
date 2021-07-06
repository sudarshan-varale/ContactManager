package com.company.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class InstructorDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String youtube_channel;
    private String hobby;
    
    
	@OneToOne(cascade = CascadeType.ALL)
    private Instructor instructor;
	
	
	
	
	
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	public String getYoutube_channel() {
		return youtube_channel;
	}
	public void setYoutube_channel(String youtube_channel) {
		this.youtube_channel = youtube_channel;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "InstructorDetails [id=" + id + ", youtube_channel=" + youtube_channel + ", hobby=" + hobby
				+ ", getYoutube_channel()=" + getYoutube_channel() + ", getHobby()=" + getHobby() + ", getId()="
				+ getId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

    
    
    
}
