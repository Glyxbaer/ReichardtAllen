package com.beowulf.ScheduleValidator.model;

import java.util.Date;

public class Lecture {

	private String name;
	private Date date;
	private int duration;
	private Course course;
	private String prof;

	public Lecture(String name, String prof) {
		this.name = name;
		this.prof = prof;
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public int getDuration() {
		return duration;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getProf() {
		return prof;
	}

	public void setProf(String prof) {
		this.prof = prof;
	}

}
